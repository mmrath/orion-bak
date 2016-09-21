package uy.montdeo.orion.database.strategy;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Class in charge of convert the Java attributes into the database tables format
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see ImprovedNamingStrategy
 * @see PhysicalNamingStrategy
 */
public class HibernateNamingStrategy extends ImprovedNamingStrategy implements PhysicalNamingStrategy {

	private static final long serialVersionUID = 933029459228152114L;

	private Locale locale = Locale.getDefault();
	private String PREFIX_TABLE = "ORION_";

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.cfg.ImprovedNamingStrategy#classToTableName(java.lang.String)
	 */
	@Override
	public String classToTableName(String className) {
		return PREFIX_TABLE + super.classToTableName(className).toUpperCase(locale);		
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.cfg.ImprovedNamingStrategy#propertyToColumnName(java.lang.String)
	 */
	@Override
	public String propertyToColumnName(String propertyName) {
		return PREFIX_TABLE + super.propertyToColumnName(propertyName).toUpperCase(locale);
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalCatalogName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSchemaName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalTableName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return Identifier.toIdentifier(classToTableName(name.getText()));
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalSequenceName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy#toPhysicalColumnName(org.hibernate.boot.model.naming.Identifier, org.hibernate.engine.jdbc.env.spi.JdbcEnvironment)
	 */
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return Identifier.toIdentifier(propertyToColumnName(name.getText()));
	}
}
