const saml = context.getVariable("request.formparam.SAMLResponse");
const samlStr = Base64.decode(saml);

context.setVariable("request.content", samlStr);
context.setVariable("request.header.Content-Type", "application/xml");
