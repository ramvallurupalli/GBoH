UPDATE ESA_NAV_SERVER
SET
  PROTOCOL = 'https',
  HOST     = '${environment-name-prefix}-dev.ifactornotifi.com',
  PORT     = '443'
WHERE
  HOST = 'localhost'
  AND PORT = '8080';
