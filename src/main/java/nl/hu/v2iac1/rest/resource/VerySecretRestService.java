package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/verysecret")
public class VerySecretRestService extends AbstractRestService {

    @GET
    @Path("/")
    public Response getSecret() {

        String output = "This is very secret: " + configuration.getValue(Configuration.Key.VERYSECRET);
        return Response.status(200).entity(output).build();

    }

}