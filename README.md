## Touchy - Deployment sensitive config

Touchy is deployment sensitive configuration library which means that it changes source of configuration depending of
detect deployment. In Touchy you can specify different sources of configuration and give them priorities so in case
of one's absence Touchy will fallback to others down to default if specified. The most important thing about touchy is
that it abstracts the way configuration is obtained so application code do not depend on any specific configuration
library or environment.

### Supported configurations

 1. System property - Touchy itself
 2. Environmental variable - Touchy itself
 3. Typesafe config

### Requirements
Touchy uses bytecode manipulation to achieve what it does so it cannot be used in environments where such thing is
not possible.

### Usage

To use Touchy all that is need to be done is to create a unit (class, abstract class or interface) which
implementation will be used to access configuration. Such unit can only declare methods taking no arguments and
returning a value (pure getters) except static methods. Currently Touchy supports strings, booleans and integers as well
 as lists of them. Config method also has to be annotated with at least one Source annotation or be implemented to
 return default value;

#### Example
Simple definition could look like this:

        public interface MyConfig {

            @Source(type = EnvVar.class, name = "MY_STRING", priority = 1);
            String getMyString();

            @Source(type = EnvVar.class, name = "MY_STRING", priority = 1);
            boolean getMyBoolean();

            @Source(type = EnvVar.class, name = "MY_STRING", priority = 1);
            int getMyInt();

        }

To create instance of this interface in program just write:

        MyConfig myConfig = Touchy.getConfig(MyConfig.class);

From now on instance myConfig can be used as normal object and will return values found in system according to
specified Source annotations.

#### Source
The most important part of Touchy is Source annotation. It has three properties that have to be specified:
 * type - class implementing interface org.sursmobil.touchy.api.ValueSource. It will be used to determine if property is
  present in deployment and to obtain its value.
 * name - name of the property. It will be passed to instance of class specified in type
 * priority - determines order in which sources are used. Priorities cannot be duplicated within single method and
 they have to be positive (grater then zero). Higher priority means that source will be used first.

When more than one Source annotation is present (and it will happen in most cases) Touchy will use them to find
actual configuration value. For example in following configuration:

        public interface MyConfig {

            @Source(type = EnvVar.class, name = "MY_STRING", priority = 1)
            @Source(type = SystemProperty.class, name = "MY_STRING", priority = 2)
            String getMyString();

        }

environmental variable MY_STRING will be used only if system property MY_STRING is not set. If value cannot be
determined (both are missing) ValueMissingException will be thrown on attempt to get that value.

#### Defaults
In many case program can use default values if configuration is missing. One could surround every call to config with
 try - catch block catching any ValueMissingException and returning default instead. Luckily it is not necessary. In
 Touchy defaults can be specified by simply defining the method. In such case configuration unit will be class or
 abstract class rather then interface, for example:

        public abstract class ConfigWithDefault {

            @Source(type = EnvVar.class, name = "MY_DEFAULT_STRING", priority = 1)
            public String getwithDefualt() {
                return "default"
            }

            @Source(type = EnvVar.class, name = "MY_STRING", priority = 1)
            public abstract String getWithoutDefault();

        }

If MY_DEFAULT_STRING environmental variable is missing implementation will be called so "default" string will be
returned. MY_STRING however is still required and ValueMissingException will be thrown on attempt to get it if it is
absent.

#### Lists
Touchy supports Lists of booleans, strings and integers as well as those types, so one can define following config:

        public interface MyConfig {

            @Source(type = EnvVar.class, name = "MY_BOOLEANS", priority = 1)
            List<Boolean> getMyBooleans();

        }

List definition is source type dependant. For example SystemProperty and EnvVar understands List as values values
separated with path.separator, so previous example could use values like 'true;false;true' in Windows or
'true:false:true' in Unix. Other configuration libraries uses other format (typesafe config has dedicated accessors),
 but it all does not matter as Touchy abstracts it from application.


#### Custom ValuesSource
Touchy uses implementations of ValueSource interface to obtain configuration values, so alone it is not strictly
configuration library. It does not provide any additional way to pass config to application, only abstracts exiting ones
. Sometimes it maybe necessary to implement custom ValueSource for specific environment (or because there is no
support for that library and Touchy). ValueSource contains set of methods to implement:
 * boolean isSet(String property) - should return true if property is defined in this source
 * Object get(String property, PropertyType type) - should return value of property parsed as specified by PropertyType
 * Object getList(String property, PropertyType type) - works similar to get method, but specified property is list
 of PropertyType

 ValueSource will use PropertyType to determine type of requested property. PropertyType contains
 Visitor interface which allow to ensure that all types supported by Touchy are implemented in ValueSource. We
 encourage to use this interface in custom implementations rather then switches to quickly find any breaking changes
 in supported types at compilation time.

### Excluding Touchy from project
If Touchy is to be excluded from project (what we do not recommend) there is nothing to do in application, only in
configuration class as Touchy is based on annotations. When you decide to not use Touchy anymore you configuration i
nhole application is still hidden behind interface so all that is needed to be done is to provide different
implementation.
