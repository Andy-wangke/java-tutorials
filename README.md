A collection of small and case-based tutorials.And this repository is specifically focused on Java area.


# How to Extend the project
1. create sub-top-level parent:
    `mvn archetype:generate \
    -DarchetypeGroupId=org.codehaus.mojo.archetypes \
    -DarchetypeArtifactId=pom-root \
    -DarchetypeVersion=RELEASE`
2. `cd` into your newly created dir.
3. For each module:
    `mvn archetype:generate \
-DarchetypeGroupId=org.apache.maven.archetypes \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DarchetypeVersion=RELEASE`
4. develop or program by self.