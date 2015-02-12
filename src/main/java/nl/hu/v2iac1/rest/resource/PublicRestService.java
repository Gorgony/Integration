package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/public")
public class PublicRestService extends AbstractRestService {

    @GET
    @Path("/")
    public Response getSecret() {

        String output = "This is public: "+ configuration.getValue(Configuration.Key.PUBLIC);
        return Response.status(200).entity(output).build();

    }

}