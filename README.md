# bidder-generation-tool

A tool that generates bidder-specific files required for PreBid Server(PBS) from a specific .json file. 
That input file should contain all necessary information provided by client(i.e. should be generated by front-end in future), as well as a path to local PBS directory.

See src/test_input.json for example of input json file with data and its format.

Currently supports static (e.g. setting a specific constant value) and dynamic transformations(values taken from bidder-specific extension) for request impressions and static transformations only for other request fields.

The tool generates following files in local PBS directory:
1. `src/main/java/org/prebid/server/bidder/{biddername}/{BidderName}Bidder.java` - java class that handles bidder request transformations;
2. `src/main/java/org/prebid/server/bidder/{biddername}/{BidderName}MetaInfo.java` - java class that describes bidder meta information;
3. `src/main/java/org/prebid/server/bidder/{biddername}/{BidderName}Usersyncer.java` - java class that handles user sync;
4. `src/main/java/org/prebid/server/spring/config/bidder/{BidderName}Configuration.java` - bidder java configuration class;
5. `src/main/java/org/prebid/server/proto/openrtb/ext/request/{biddername}/ExtImp{BidderName}.java` - java class that is a model for bidder-specific extension, passed in request.imp.ext.bidder;
6. `src/main/resources/bidder-config/{biddername}.yaml` - bidder configuration properties file;
7. `src/main/resources/static/bidder-params/{bidderName}.json` - bidder json schema that describes bidder-specific parameters;
8. `src/test/java/org/prebid/server/bidder/{biddername}/{BidderName}UsersyncerTest.java` - java test class for bidder's Usersyncer;
9. `src/test/java/org/prebid/server/bidder/{biddername}/{BidderName}BidderTest.java` - java test class for Bidder class**

 ***NOTE: ATM generates tests only for cases when bidder has no extension and doesn't apply any transformations.**

Prerequisites:
- Java 8+
- Maven 3.3+

Requires input json file(in corresponding format) to be passed in requested body and PBS server files locally on user's PC(a path to directory should be specified in previously-mentioned json file).

Steps:

1. Clone the bidder-generation-tool repository with `git@github.com:rubicon-project/pbs-java-bidder-generation.git` command

2. Run with command `mvn spring-boot:run`

3. Send a POST request to `http://localhost:8080/generate` with a header `Content-Type : application/json` and json body containing bidder description properties and local PBS directory path.

4. Check the results in your local PBS directory or project.
