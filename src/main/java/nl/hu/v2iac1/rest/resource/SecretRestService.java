package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/secret")
public class SecretRestService extends AbstractRestService{



    @GET
    @Path("/")
    public Response getSecret() {

        String output = "This is the secret: " + configuration.getValue(Configuration.Key.SECRET);
        return Response.status(200).entity(output).build();

    }

}