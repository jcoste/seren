package net.thecodersbreakfast.seren.filter;

import javassist.CtClass;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * A {@link ClassFilter} that accepts classes based on their name.
 * <p/>
 * This filter accepts the following configuration parameters :
 * <ul>
 * <li>pattern : a regular expression defining the accepted class names. (Remember to double the backslashes!) </li>
 * </ul>
 * <p/>
 * Example :
 * <pre>
 *     filter.&lt;filterId&gt;.pattern=.*\\..*Model
 * </pre>
 *
 * @author Olivier Croisier
 */
public class ClassPatternFilter extends BaseClassFilter {

    private Pattern pattern;

    @Override
    public void configure(Map<String, String> config) {
        String packagePattern = config.get("pattern");
        pattern = Pattern.compile(packagePattern);
    }

    @Override
    public boolean acceptClass(ClassLoader classLoader, CtClass classDefinition) throws Exception {
        return super.acceptClass(classLoader, classDefinition) && pattern.matcher(classDefinition.getName()).matches();
    }
}
