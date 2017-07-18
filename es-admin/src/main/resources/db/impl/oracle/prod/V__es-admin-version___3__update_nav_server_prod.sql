UPDATE ESA_NAV_SERVER
SET
  PROTOCOL = 'https',
  HOST     = '${environment-name-prefix}.ifactornotifi.com',
  PORT     = '443'
WHERE
  HOST = 'localhost'
  AND PORT = '8080';
/

UPDATE ESA_NAV_SERVER
SET
  HOST     = 'ifactorconsulting.looker.com'
WHERE
  HOST     = 'ifactorstaging.looker.com';
/