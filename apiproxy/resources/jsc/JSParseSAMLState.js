var samlState = context.getVariable("samlresp.state");
var authzParams = {};
try {
    authzParams = JSON.parse(samlState);
} catch (e) {
}

context.setVariable("authzparams.response_type", authzParams.response_type);
context.setVariable("authzparams.client_id", authzParams.client_id);
context.setVariable("authzparams.redirect_uri", authzParams.redirect_uri);
context.setVariable("authzparams.scope", authzParams.scope);
context.setVariable("authzparams.state", authzParams.state);
