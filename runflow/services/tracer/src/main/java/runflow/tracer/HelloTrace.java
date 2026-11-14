package runflow.tracer;
/** Phase 0 exit-demo stub: prints a minimal valid trace JSON ("hello" trace). Real JDI tracer arrives in Phase 2. */
public class HelloTrace {
    public static void main(String[] args) {
        System.out.println("{\"schemaVersion\":1,\"status\":\"SUCCESS\",\"message\":null,"
            + "\"meta\":{\"compileMs\":0,\"runMs\":0,\"totalSteps\":0,\"truncated\":false,\"keyframeEvery\":25},"
            + "\"diagnostics\":[],\"graph\":{\"methods\":[],\"nodes\":[],\"edges\":[]},"
            + "\"pointerHints\":[],\"steps\":[]}");
    }
}
