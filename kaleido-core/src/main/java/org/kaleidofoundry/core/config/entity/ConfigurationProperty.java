/*
 *  Copyright 2008-2011 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.kaleidofoundry.core.config.entity;

import static org.kaleidofoundry.core.config.entity.ConfigurationModelConstants.Entity_Property;
import static org.kaleidofoundry.core.config.entity.ConfigurationModelConstants.Table_Property;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.kaleidofoundry.core.config.AbstractConfiguration;
import org.kaleidofoundry.core.config.entity.ConfigurationModelConstants.Query_FindPropertyByName;
import org.kaleidofoundry.core.lang.annotation.Task;
import org.kaleidofoundry.core.util.PrimitiveTypeToStringSerializer;
import org.kaleidofoundry.core.util.ToStringSerializer;

/**
 * Meta model of a configuration property
 * 
 * @author Jerome RADUGET
 */
@Entity(name = Entity_Property)
// @Access(AccessType.PROPERTY)
@Table(name = Table_Property)
@NamedQueries({ @NamedQuery(name = Query_FindPropertyByName.Name, query = Query_FindPropertyByName.Jql) })
@XmlRootElement(name = "property")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "name", "value", "type", "description" })
@Task(comment = "Audit information (locale zone for the date, user information...)")
public class ConfigurationProperty implements Serializable {

   private static final long serialVersionUID = 2271419559641887975L;

   @Id
   @GeneratedValue
   private Long id;
   @Column(name = "NAME")
   private String name;
   @XmlTransient
   @ManyToMany(fetch = FetchType.LAZY, mappedBy = "properties")
   private Set<ConfigurationModel> configurations;
   private String value;
   private String type;
   private String description;
   @Version
   @XmlTransient
   Integer version;
   @Transient
   @XmlTransient
   private final ToStringSerializer serializer;

   /**
    * 
    */
   public ConfigurationProperty() {
	this(null, null, null, null);
   }

   /**
    * @param name property name
    * @param type property type
    */
   public ConfigurationProperty(final String name, final Class<?> type) {
	this(name, type, null);
   }

   /**
    * @param name property name
    * @param type property type
    * @param description optional description
    */
   public ConfigurationProperty(final String name, final Class<?> type, final String description) {
	this(name, null, type, description);
   }

   /**
    * @param name property name
    * @param value property value
    * @param type property type
    * @param description optional description
    */
   public ConfigurationProperty(final String name, final Serializable value, final Class<?> type, final String description) {
	super();
	setName(name);
	this.description = description;
	this.type = type != null ? type.getName() : null;
	this.configurations = new HashSet<ConfigurationModel>();
	this.serializer = new PrimitiveTypeToStringSerializer();
	setValue(value);
   }

   /**
    * @return the configuration of the property
    */
   public Set<ConfigurationModel> getConfigurations() {
	return configurations;
   }

   /**
    * @return persistent identifier
    */
   public Long getId() {
	return id;
   }

   /**
    * @return property name
    */
   public String getName() {
	return name;
   }

   /**
    * @return property description
    */
   public String getDescription() {
	return description;
   }

   /**
    * @param configurations the configuration to set
    */
   public void setConfigurations(final Set<ConfigurationModel> configurations) {
	this.configurations = configurations;
   }

   /**
    * @param id the persistent id to set
    */
   public void setId(final Long id) {
	this.id = id;
   }

   /**
    * @param name the property to set
    */
   public void setName(final String name) {
	if (name != null) {
	   this.name = AbstractConfiguration.normalizeKey(name);
	} else {
	   this.name = null;
	}
   }

   /**
    * @param description the description to set
    */
   public void setDescription(final String description) {
	this.description = description;
   }

   /**
    * @return the property value
    */
   public Serializable getValue() {
	return serializer.deserialize(value, getType());
   }

   /**
    * @param value the property value to set
    */
   public <T extends Serializable> void setValue(final T value) {
	Class<T> type = getType();
	if (type != null) {
	   this.value = serializer.serialize(value, type);
	}
   }

   /**
    * @return the property type
    */
   @SuppressWarnings("unchecked")
   public <T extends Serializable> Class<T> getType() {
	if (type == null) { return null; }
	try {
	   return (Class<T>) Class.forName(type);
	} catch (ClassNotFoundException cnfe) {
	   throw new IllegalStateException(cnfe);
	}
   }

   /**
    * @param type the property type
    */
   public <T extends Serializable> void setType(final Class<T> type) {
	this.type = type != null ? type.getName() : null;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((configurations == null) ? 0 : configurations.hashCode());
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object obj) {
	if (this == obj) { return true; }
	if (obj == null) { return false; }
	if (!(obj instanceof ConfigurationProperty)) { return false; }
	ConfigurationProperty other = (ConfigurationProperty) obj;
	if (configurations == null) {
	   if (other.configurations != null) { return false; }
	} else if (!configurations.equals(other.configurations)) { return false; }
	if (description == null) {
	   if (other.description != null) { return false; }
	} else if (!description.equals(other.description)) { return false; }
	if (name == null) {
	   if (other.name != null) { return false; }
	} else if (!name.equals(other.name)) { return false; }
	if (type == null) {
	   if (other.type != null) { return false; }
	} else if (!type.equals(other.type)) { return false; }
	if (value == null) {
	   if (other.value != null) { return false; }
	} else if (!value.equals(other.value)) { return false; }
	return true;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
	return "ConfigurationProperty [name=" + name + ", value=" + value + ", type=" + type + ", description=" + description + "]";
   }

}
