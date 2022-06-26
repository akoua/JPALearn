package com.example.caveatemptor.entity.converter;

import com.example.caveatemptor.entity.others.MonetaryAmount;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

public class MonetaryAmountUserType implements CompositeUserType, DynamicParameterizedType {

    // La dévise cible renseigné dans l'annotation correspondante
    private Currency convertTo;

    /**
     * Nous pouvons implémenter n'importe quelle routine de conversion de devises dont nous avons besoin.
     * Pour les besoins de l'exemple, nous doublons la valeur afin de pouvoir facilement tester si la conversion
     * a réussi. Nous devrons remplacer ce code par un véritable convertisseur de devises dans une application
     * réelle. Cette méthode convert de l'API Hibernate userType.
     */
    private MonetaryAmount convert(MonetaryAmount amount, Currency toCurrency) {
        return new MonetaryAmount(amount.getValue().multiply(new BigDecimal(2)), toCurrency);
    }

    @Override
    public void setParameterValues(Properties properties) {
        System.out.println("setParameterValues: " + properties);
        String convertToParameter = properties.getProperty("convertTo");
        this.convertTo = Currency.getInstance(convertToParameter != null ? convertToParameter : "USD");
    }

    /**
     * Les méthodes restantes fournies de CompositeUserType fournissent les détails des propriétés MonetaryAmount,
     * afin qu’hibernale puisse insérer la classe au moteur de requête.
     *
     * @return un tableau String avec deux éléments, ‘value’ et ‘currency’ les noms des propriétés de la classe MonetaryAmount.
     */
    @Override
    public String[] getPropertyNames() {
        return new String[]{"value", "currency"};
    }

    /**
     * A l'instar de {@link this#getPropertyNames()} permet de retourner les types de la classe
     *
     * @return [StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY]
     */
    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.CURRENCY};
    }

    /**
     * Renvoie soit le champ de valeur, soit le champ de conception de l’objet MonetaryAmount,
     * selon l’index de propriété
     * Code pouvant ètre en erreur en cas de remaniement des propriétés de MonetaryAmount
     */
    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        MonetaryAmount monetaryAmount = (MonetaryAmount) component;
        System.out.println("getPropertyValue: " + property + "  " + monetaryAmount);
        if (property == 0) {
            return monetaryAmount.getValue();
        } else {
            return monetaryAmount.getCurrency();
        }
    }

    /**
     * This method will not allow setting any field of the MonetaryAmount object, as this one is immutable
     */
    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {
        throw new UnsupportedOperationException("MonetaryAmount is immutable");
    }

    /**
     * Elle adapte la classe donnée, dans ce cas, MonetaryAmount.
     */
    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    /**
     * Hibernate utilise l’égalité des valeurs pour déterminer si la valeur a été modifiée et
     * si la BD doit être mise à jour. Nous nous appuyons sur les routines d’égalité et
     * de code de hachage que nous avons déjà écrite sur la classe MonetaryAmount
     */
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y || !(x == null || y == null) && x.equals(y);
    }

    /**
     * Hibernate utilise l’égalité des valeurs pour déterminer si la valeur a été modifiée et
     * si la BD doit être mise à jour. Nous nous appuyons sur les routines d’égalité et
     * de code de hachage que nous avons déjà écrite sur la classe MonetaryAmount
     */
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    /**
     * La méthode nullSafeGet est appelée pour lire le ResultSet lorsqu’une
     * valeur MonetaryAmount doit être extraite de la BD.
     */
    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sessions, Object owner) throws HibernateException, SQLException {
        System.out.println("nullSafeGet: " + names);
        // Nous prenons le montant et les valeurs monétaires tels qu’ils sont indiqués dans le résultat de la requête.
        BigDecimal amount = resultSet.getBigDecimal(names[0]);
        if (resultSet.wasNull()) {
            return null;
        }
        Currency currency = Currency.getInstance(resultSet.getString(names[1]));
        return new MonetaryAmount(amount, currency);
    }

    /**
     * La méthode nullSafeSet est appelée lorsqu’une
     * valeur MonetaryAmount doit être stockée de la BD.
     *
     * @param statement requête préparée
     * @param value     l'object à enregistrer
     * @param index     la position de la propriété dans la class ou comme defini avec {@link this#getPropertyNames()}
     * @param session   la session de la Bd en cours
     * @throws HibernateException
     * @throws SQLException
     */
    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            statement.setNull(
                    index,
                    StandardBasicTypes.BIG_DECIMAL.sqlType()
            );
            statement.setNull(
                    index + 1,
                    StandardBasicTypes.CURRENCY.sqlType()
            );
        } else {
            MonetaryAmount amount = (MonetaryAmount) value;
            MonetaryAmount dbAmount = convert(amount, convertTo);
            // en fonction nous insérons dans les colonnes exactes
            statement.setBigDecimal(index, amount.getValue());
            statement.setString(index + 1, convertTo.getCurrencyCode());

        }
    }

    /**
     * Si Hibernate doit faire une copie de la valeur, il appelle cette méthode deepCopy.
     * Pour les classes immuables simples comme MonetaryAmount, nous pouvons retourner l’instance donnée
     */
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    /**
     * Hibernate can enable some optimisations if it knows that MonetaryAmount is immutable
     */
    @Override
    public boolean isMutable() {
        return false;
    }

    /**
     * Hibernate calls the disassemble method when it stores a value in the global shared second-level cache.
     * We need to return a Serializable representation. For MonetaryAmount, a String representation is an easy solution.
     * Or, because MonetaryAmount is Serializable, we could return it directly.
     */
    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return value.toString();
    }

    /**
     * Hibernate appelle la méthode d’assemblage lorsqu’il lit la représentation sérialisée à partir du cache
     * global partagé de second niveau. Nous créons une instance MonetaryAmount à partir de la représentation
     * String. Ou, si nous stockons un MonetaryAmount sérialisé, nous pouvons le retourner directement.
     */
    @Override
    public Object assemble(Serializable cache, SharedSessionContractImplementor sharedSessionContractImplementor, Object owner) throws HibernateException {
        return MonetaryAmount.fromString((String) cache);
    }

    /**
     * La méthode de remplacement est appelée lors des opérations EntityManager#merge().
     * Nous devons retourner une copie de l’original. Ou si le type de la valeur est immuable,
     * comme MonetaryAmount, nous pouvons renvoyer l’original.
     */
    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor sharedSessionContractImplementor, Object owner) throws HibernateException {
        return original;
    }
}
