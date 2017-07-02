[INFO] Scanning for projects...
[WARNING] 
[WARNING] Some problems were encountered while building the effective model for org.test:demo:jar:0.0.1-SNAPSHOT
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.spockframework:spock-core:jar -> duplicate declaration of version 1.0-groovy-2.4 @ line 101, column 15
[WARNING] 
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING] 
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING] 
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building bookpub_chap05_f_spock 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ demo ---
[INFO] Deleting /Users/JatinPatel/Documents/Study/Spring4/cookbook/bookpub_chap05_f_spock/target
[INFO] 
[INFO] --- gmavenplus-plugin:1.5:addTestSources (default) @ demo ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ demo ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.3:compile (default-compile) @ demo ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 17 source files to /Users/JatinPatel/Documents/Study/Spring4/cookbook/bookpub_chap05_f_spock/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ demo ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 6 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.3:testCompile (default-testCompile) @ demo ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 7 source files to /Users/JatinPatel/Documents/Study/Spring4/cookbook/bookpub_chap05_f_spock/target/test-classes
[INFO] 
[INFO] --- gmavenplus-plugin:1.5:testCompile (default) @ demo ---
[INFO] Using Groovy 2.4.4 to perform testCompile.
[INFO] Compiled 1 file.
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ demo ---
