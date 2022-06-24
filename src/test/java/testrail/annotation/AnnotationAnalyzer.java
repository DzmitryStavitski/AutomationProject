package testrail.annotation;

import java.lang.reflect.Method;

public class AnnotationAnalyzer {
    public static int parse(Method method) {
        if(method.isAnnotationPresent(TestCaseId.class)) {
            TestCaseId annotation = method.getAnnotation(TestCaseId.class);
            return annotation.caseId();
        }
        return 0;
    }
}
