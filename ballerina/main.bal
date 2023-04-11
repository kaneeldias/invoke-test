import ballerina/io;
import ballerina/jballerina.java;
import ballerina/http;

public client class TestClient {

    isolated function callBMethod() returns string = @java:Method {
        'class: "io.ballerina.stdlib.test_project.Utils",
        name: "callBMethod"
    } external;

    isolated function getString() returns string {
        io:println("Inside Ballerina getString method");
        return "Hello";
    }
}


public function main() returns error? {
    io:println("Inside Ballerina main method");
    TestClient testClient = new;
    string result = testClient.callBMethod();
    io:println(result);
}

service / on new http:Listener(8080) {
    resource function get callBMethod() returns error? {
        io:println("Inside Ballerina service resource method");
        TestClient testClient = new;
        string result = testClient.callBMethod();
        io:println(result);
    }
}

