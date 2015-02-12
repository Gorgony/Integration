package nl.hu.v2iac1.rest.resource;

import nl.hu.v2iac1.Configuration;

import javax.inject.Inject;

/**
 * Created by Roelant Ossewaarde on 2/9/15.
 */
public abstract class AbstractRestService {
    @Inject
    protected
    Configuration configuration;
}
