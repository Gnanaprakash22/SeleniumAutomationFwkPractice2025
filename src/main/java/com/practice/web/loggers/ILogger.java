package com.practice.web.loggers;

public interface ILogger {
    
    /**
     * Log an informational message
     * @param message the message to log
     */
    void info(String message);
    
    /**
     * Log an error message
     * @param message the error message to log
     */
    void error(String message);
    
    /**
     * Log a warning message
     * @param message the warning message to log
     */
    void warn(String message);
    
    /**
     * Log a debug message
     * @param message the debug message to log
     */
    void debug(String message);
    
    /**
     * Log a test step
     * @param stepDescription description of the test step
     */
    void step(String stepDescription);
    
    /**
     * Log test start event
     * @param testName name of the test being started
     */
    void testStart(String testName);
    
    /**
     * Log test end event
     * @param testName name of the test being ended
     */
    void testEnd(String testName);
    
    /**
     * Log a browser action
     * @param action the action performed (click, sendKeys, etc.)
     * @param element the element on which action is performed
     */
    void browserAction(String action, String element);
    
    /**
     * Log an assertion result
     * @param description description of the assertion
     * @param result true if assertion passed, false if failed
     */
    void assertion(String description, boolean result);
    
    /**
     * Attach a screenshot with message
     * @param message the message to accompany the screenshot
     */
    void attachScreenshot(String message);
    
    /**
     * Attach a file with content
     * @param message the message to accompany the file
     * @param content the file content as string
     * @param fileName the name of the file
     */
    void attachFile(String message, String content, String fileName);
    
    /**
     * Log an API request/response
     * @param endpoint the API endpoint
     * @param method the HTTP method (GET, POST, etc.)
     * @param requestBody the request body (can be null)
     * @param responseBody the response body (can be null)
     */
    void apiLog(String endpoint, String method, String requestBody, String responseBody);
    
    /**
     * Log error with screenshot attachment
     * @param message the error message
     */
    void errorWithScreenshot(String message);
    
    /**
     * Log pass status
     * @param message the pass message
     */
    void pass(String message);
    
    /**
     * Log fail status
     * @param message the fail message
     */
    void fail(String message);
    
    /**
     * Log skip status
     * @param message the skip message
     */
    void skip(String message);
}
