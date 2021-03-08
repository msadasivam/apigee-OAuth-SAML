// ID as a non-guessable, non-repeatable pointer to input params
var responseType = context.getVariable("request.queryparam.response_type");
var clientId = context.getVariable("request.queryparam.client_id");
var redirectUri = context.getVariable("request.queryparam.redirect_uri");
var scope = context.getVariable("request.queryparam.scope");
var state = context.getVariable("request.queryparam.state");
var randomNumber = Math.floor(100000 + Math.random() * 900000);
var timestamp = context.getVariable("system.timestamp");
var text = responseType + clientId + redirectUri + scope + randomNumber + timestamp;

var _sha512 = crypto.getSHA512();
_sha512.update(text);
var idHash = _sha512.digest();

//var idHash = CryptoJS.SHA512(text).toString(CryptoJS.enc.Hex);
var samlID = idHash.substring(1, 30);
context.setVariable("samlinfo.id", "_" + samlID);

var samlState = {
    response_type: responseType,
    client_id: clientId,
    redirect_uri: redirectUri,
    scope: scope,
    state: state,
    startTime: timestamp
};
context.setVariable("samlinfo.state", JSON.stringify(samlState));


