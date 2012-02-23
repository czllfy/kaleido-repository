# update child version after updating parent version
mvn -N versions:update-child-modules

# signing gpg 
# https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven

# releasing
# https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide

mvn clean deploy -Dgpg.keyname=thekeyname -Dgpg.passphrase=thephrase
mvn release:clean
mvn release:prepare
mvn release:rollback
mvn release:perform -Dgpg.keyname=thekeyname -Darguments=-Dgpg.passphrase=thephrase


# site and google code upload
mvn clean site:site && mvn site:stage
mvn site:deploy 

