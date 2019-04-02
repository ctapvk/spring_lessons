# repo уроков
```php
git clone https://github.com/ydvorzhetskiy/spring-framework-01
# переходим в папку и делаем checkout в Final (HEAD) 
git h
git checkout
# удалаем папку гита
rm -rf .git
```


# сборка проекта make-jar-with-dependencies
```php
# собираем и запускаем
mvn clean package
java -jar target/*jar-with-dependencies.jar
```
### добавть в `pom.xml`  заменив `mainClass`
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>ru.otus.spring03.Main</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-jar-with-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
	    </plugins>
    </build>
```


