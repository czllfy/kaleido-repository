ALTER TABLE CONFIGURATION_PROPERTIES DROP CONSTRAINT CNFGRTNPRPRPRPRTYD
ALTER TABLE CONFIGURATION_PROPERTIES DROP CONSTRAINT CNFGRTNPRPCNFGRTND
ALTER TABLE CONFIGURATION DROP CONSTRAINT UNQ_CONFIGURATION_0
ALTER TABLE CONFIGURATION DROP CONSTRAINT UNQ_CONFIGURATION_1
ALTER TABLE I18N_LANGUAGE DROP CONSTRAINT 18NLANGUAGEMSSGEID
ALTER TABLE I18N_LANGUAGE DROP CONSTRAINT UNQ_I18N_LANGUAGE_0
ALTER TABLE I18N_ENTRY DROP CONSTRAINT 18NENTRYGROUPECODE
ALTER TABLE I18N_ENTRY DROP CONSTRAINT UNQ_I18N_ENTRY_0
ALTER TABLE I18N_ENTRY_I18N_LANGUAGE DROP CONSTRAINT 18NNTRY18N18nMssgD
ALTER TABLE I18N_ENTRY_I18N_LANGUAGE DROP CONSTRAINT 18NNTRY1mssgLnggsD
ALTER TABLE I18N_GROUP DROP CONSTRAINT 18NGROUPPARENTCODE
ALTER TABLE I18N_GROUP DROP CONSTRAINT 18NGRUPDSCRPTIONID
DROP TABLE CONFIGURATION_PROPERTIES
DROP TABLE CONFIGURATION
DROP TABLE I18N_LANGUAGE
DROP TABLE I18N_ENTRY
DROP TABLE FILESTORE
DROP TABLE CONFIGURATION_PROPERTY
DROP TABLE I18N_ENTRY_I18N_LANGUAGE
DROP TABLE I18N_GROUP
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
