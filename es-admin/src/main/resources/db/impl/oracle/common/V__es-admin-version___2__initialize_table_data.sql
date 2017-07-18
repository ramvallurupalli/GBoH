-- ESA_AUTHORITY
BEGIN
  upsertESA_AUTHORITY('IFACTOR_INTERNAL', 'iFactor Internal');
  upsertESA_AUTHORITY('ES_USERS_WRITE', 'Create users (not super admins)');
  upsertESA_AUTHORITY('STORMCENTER', 'Storm Center');
  upsertESA_AUTHORITY('NOTIFI_TEMPLATES_READ', 'Read Notifi Templates');
  upsertESA_AUTHORITY('NOTIFI_TEMPLATES_WRITE', 'Write Notifi Templates');
  upsertESA_AUTHORITY('NOTIFI_MUTE_READ', 'Read Notifi Mute rules');
  upsertESA_AUTHORITY('NOTIFI_MUTE_WRITE', 'Write Notifi rules');
  upsertESA_AUTHORITY('NOTIFI_BROADCAST_SEND', 'Send Notifi Broadcast');
  upsertESA_AUTHORITY('NOTIFI_HISTORY', 'View Notifi message history');
  upsertESA_AUTHORITY('NOTIFI_REPORTING', 'View Notifi reporting');
  upsertESA_AUTHORITY('BPU_CREWMAP', 'View BPU Crew Map');
  upsertESA_AUTHORITY('BPU_THRESHOLDS_READ', 'Read BPU Thresholds');
  upsertESA_AUTHORITY('BPU_THRESHOLDS_WRITE', 'Write BPU Thresholds');
  upsertESA_AUTHORITY('BPU_CONTACTS_PRIVATE_READ', 'Read Internal BPU Contacts');
  upsertESA_AUTHORITY('BPU_CONTACTS_PRIVATE_WRITE', 'Write Internal BPU Contacts');
  upsertESA_AUTHORITY('BPU_CONTACTS_PUBLIC_READ', 'Read BPU Contacts');
  upsertESA_AUTHORITY('BPU_CONTACTS_PUBLIC_WRITE', 'Write BPU Contacts');
  upsertESA_AUTHORITY('NOTIFI_PREFERENCES_WRITE', 'Manage account preferences');
  upsertESA_AUTHORITY('NOTIFI_REST', 'Notifi REST API');
  upsertESA_AUTHORITY('NOTIFI_ENROLLMENTS_READ', 'View enrollments');
  upsertESA_AUTHORITY('NOTIFI_SOAP', 'Notifi SOAP API');
  upsertESA_AUTHORITY('AGGREGATOR', 'Communication aggregator');
  upsertESA_AUTHORITY('OAUTH2_CLIENT', 'OAuth2 Client');
END;
/

-- ESA_NAV_APPLICATION
BEGIN
  upsertESA_NAV_APPLICATION('NOTIFI', 'Notifi');
  upsertESA_NAV_APPLICATION('STORMCENTER', 'Storm Center');
  upsertESA_NAV_APPLICATION('ES', 'Admin');
END;
/

-- ESA_NAV_SERVER
BEGIN
  upsertESA_NAV_SERVER('Notifi Admin', 'http', 'localhost', '8080', '/admin/notifi', '/logout');
  upsertESA_NAV_SERVER('Notifi', 'http', 'localhost', '8080', '/notifi', '/secure/logout');
  upsertESA_NAV_SERVER('Admin', 'http', 'localhost', '8080', '/admin/es', '/logout');
  upsertESA_NAV_SERVER('Storm Center', 'http', 'localhost', '8080', '/stormcenter/s3/sc-admin', '/logout');
  upsertESA_NAV_SERVER('KUBRA', 'https', 'www.kubra.com', '443', ' ', NULL);
  upsertESA_NAV_SERVER('KUBRA Looker','http', 'localhost', '8080', '/admin/notifi', NULL);
  upsertESA_NAV_SERVER('Broadcast', 'http', 'localhost', '8080', '/blitz', NULL);
END;
/

-- ESA_NAV_VIEW
BEGIN
  upsertESA_NAV_VIEW('USER_MANAGEMENT', 'User Management', '/security#/users', 'info', 'Admin', 'ES', 10, '0');
  upsertESA_NAV_VIEW('ROLE_MANAGEMENT', 'Role Management', '/security#/roles', 'info', 'Admin', 'ES', 15, '0');

  upsertESA_NAV_VIEW('HISTORY', 'History', '/secure/#history', 'clock', 'Notifi', 'NOTIFI', 20, '0');
  upsertESA_NAV_VIEW('BROADCAST', 'Broadcast', '/secure/#broadcast', 'info', 'Notifi', 'NOTIFI', 30, '0');
  upsertESA_NAV_VIEW('TEMPLATES', 'Templates', '/secure/#templates', 'layers', 'Notifi', 'NOTIFI', 40, '0');
  upsertESA_NAV_VIEW('SUPPORT', 'Support', '/secure/support', 'gear', 'Notifi', 'NOTIFI', 50, '0');
  upsertESA_NAV_VIEW('PREFERENCES', 'Preferences', '/secure/#preferences', 'bell', 'Notifi', 'NOTIFI', 190, '0');
  upsertESA_NAV_VIEW('NOTIFIADMIN', 'Admin', '/secure/#notifiadmin', 'gear', 'Notifi', 'NOTIFI', 200, '0');
  upsertESA_NAV_VIEW('REPORTS', 'Reports', '/#/reports', 'layers', 'KUBRA Looker', 'NOTIFI', 210, '0');
  upsertESA_NAV_VIEW('ENROLLMENTS', 'Enrollments', '/secure/#enrollments', 'clock', 'Notifi', 'NOTIFI', 220, '0');

  upsertESA_NAV_VIEW('SC_ALERTS', 'Alerts', '/alerts/index.html#/', 'bell', 'Storm Center', 'STORMCENTER', 500, '0');
  upsertESA_NAV_VIEW('CUSTOM_LAYERS', 'Custom Layers', '/custom-layers/index.html#/', 'layers', 'Storm Center',
                     'STORMCENTER', 510, '0');
  upsertESA_NAV_VIEW('EVENT_HISTORY', 'Event History', '/events-manager/index.html#/', 'clock', 'Storm Center',
                     'STORMCENTER', 520, '0');
  upsertESA_NAV_VIEW('SC_SETTINGS', 'Settings', '/settings/index.html#/', 'info', 'Storm Center', 'STORMCENTER', 530,
                     '0');
  upsertESA_NAV_VIEW('STORM_MANAGER', 'Storm Manager', '/storm-manager/index.html#/', 'warning', 'Storm Center',
                     'STORMCENTER', 540, '0');
END;
/


-- ESA_AUTHORITY_NAV_VIEW;
BEGIN
  upsertESA_AUTHORITY_NAV_VIEW('IFACTOR_INTERNAL', 'ROLE_MANAGEMENT');
  upsertESA_AUTHORITY_NAV_VIEW('IFACTOR_INTERNAL', 'SUPPORT');
  upsertESA_AUTHORITY_NAV_VIEW('ES_USERS_WRITE', 'USER_MANAGEMENT');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_TEMPLATES_READ', 'TEMPLATES');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_TEMPLATES_WRITE', 'TEMPLATES');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_MUTE_WRITE', 'NOTIFIADMIN');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_BROADCAST_SEND', 'BROADCAST');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_HISTORY', 'HISTORY');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_REPORTING', 'REPORTS');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_PREFERENCES_WRITE', 'PREFERENCES');
  upsertESA_AUTHORITY_NAV_VIEW('NOTIFI_ENROLLMENTS_READ', 'ENROLLMENTS');
  upsertESA_AUTHORITY_NAV_VIEW('STORMCENTER', 'SC_ALERTS');
  upsertESA_AUTHORITY_NAV_VIEW('STORMCENTER', 'CUSTOM_LAYERS');
  upsertESA_AUTHORITY_NAV_VIEW('STORMCENTER', 'SC_SETTINGS');
  upsertESA_AUTHORITY_NAV_VIEW('STORMCENTER', 'EVENT_HISTORY');
  upsertESA_AUTHORITY_NAV_VIEW('STORMCENTER', 'STORM_MANAGER');
END;
/

-- ESA_ROLE
BEGIN
  upsertESA_ROLE('IFACTOR', 'iFactor Employee');
  upsertESA_ROLE('USER', 'User');
  upsertESA_ROLE('ADMINISTRATOR', 'Administrator');
  upsertESA_ROLE('NOTIFI_WS', 'PSEGLINY Utility');
  upsertESA_ROLE('AGGREGATOR', 'Aggregators');
  upsertESA_ROLE('SSO_CLIENT', 'OAuth2 Client Applications');
END;
/

-- ESA_ROLE_AUTHORITY
BEGIN
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_CONTACTS_PRIVATE_READ');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_CONTACTS_PRIVATE_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_CONTACTS_PUBLIC_READ');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_CONTACTS_PUBLIC_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_CREWMAP');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_THRESHOLDS_READ');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'BPU_THRESHOLDS_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'ES_USERS_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_BROADCAST_SEND');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_ENROLLMENTS_READ');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_HISTORY');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_MUTE_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_PREFERENCES_WRITE');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'NOTIFI_REPORTING');
  upsertESA_ROLE_AUTHORITY('ADMINISTRATOR', 'STORMCENTER');
  upsertESA_ROLE_AUTHORITY('AGGREGATOR', 'AGGREGATOR');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'AGGREGATOR');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_CONTACTS_PRIVATE_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_CONTACTS_PRIVATE_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_CONTACTS_PUBLIC_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_CONTACTS_PUBLIC_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_CREWMAP');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_THRESHOLDS_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'BPU_THRESHOLDS_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'ES_USERS_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'IFACTOR_INTERNAL');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_BROADCAST_SEND');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_ENROLLMENTS_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_HISTORY');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_MUTE_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_MUTE_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_PREFERENCES_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_REPORTING');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_REST');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_SOAP');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_TEMPLATES_READ');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'NOTIFI_TEMPLATES_WRITE');
  upsertESA_ROLE_AUTHORITY('IFACTOR', 'STORMCENTER');
  upsertESA_ROLE_AUTHORITY('NOTIFI_WS', 'NOTIFI_REST');
  upsertESA_ROLE_AUTHORITY('NOTIFI_WS', 'NOTIFI_SOAP');
  upsertESA_ROLE_AUTHORITY('SSO_CLIENT', 'OAUTH2_CLIENT');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_CONTACTS_PRIVATE_READ');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_CONTACTS_PRIVATE_WRITE');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_CONTACTS_PUBLIC_READ');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_CONTACTS_PUBLIC_WRITE');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_CREWMAP');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_THRESHOLDS_READ');
  upsertESA_ROLE_AUTHORITY('USER', 'BPU_THRESHOLDS_WRITE');
  upsertESA_ROLE_AUTHORITY('USER', 'NOTIFI_ENROLLMENTS_READ');
  upsertESA_ROLE_AUTHORITY('USER', 'NOTIFI_HISTORY');
  upsertESA_ROLE_AUTHORITY('USER', 'NOTIFI_MUTE_READ');
  upsertESA_ROLE_AUTHORITY('USER', 'NOTIFI_PREFERENCES_WRITE');
  upsertESA_ROLE_AUTHORITY('USER', 'STORMCENTER');
END;
/

-- ESA_USER (users 1-7)
BEGIN
  upsertESA_USER('ifactor', 'V4cbdZKWNVgXmsgndsYFXXE2sDmAlWK4R7KcvmOiIwo=', 'WkpokFmfhQBjfYrppRfobw==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'ifactor@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('awesome', 'vFPDwCUfvRMDRmypvcSHP/mR3pYEhhOn440twYz/t8o=', '/qRoHybBE+erGFNk6wMrpA==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'awesome@ifactorinc.com',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('notifi', 's3zjk2zOlrQQSJ+14kf5cUj5JLcycW4FPxe2Ia5HjBw=', 'Zc1zFY6qUNLPC+Yl4VZQhw==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'notifi@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('notifi-admin', 'NixoHWgP9EBEQFB5WyL4AMKjLxtMefk96oT24lhzLOw=', '5mhqqOjVSNSTqHb0occepA==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'notifi-admin@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('bigoh_client', 'XNttCiwMban1bq9BFXha84Ybsx8y/NLX9ppZGGEchNc=', 'W/33d8AD16TMW5Qkqa4cmA==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'bigoh_client@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('stitch_client', 'jyiJc1MO+xwZHGgwRf/1dNduPECATbGb8AynXdMOynI=', 'OQsksXUmFP6qBztZHLvhkw==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'stitch_client@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
  upsertESA_USER('sc-admin', 'UoYKud9L+xyFj8NAP54cpGKAQOeVFR19yh5cYZacI+c=', 'M3lD+djUBIzF9h8JYNeHOg==', '1',
                 '01-JAN-45 12.00.00.000000000 AM', NULL, NULL, NULL, 'sc-admin@sink.sendgrid.net',
                 '01-JAN-45 12.00.00.000000000 AM', '1', NULL, NULL);
END;
/

-- ESA_USER_ROLE
BEGIN
  upsertESA_USER_ROLE('ifactor', 'IFACTOR');
  upsertESA_USER_ROLE('ifactor', 'USER');
  upsertESA_USER_ROLE('ifactor', 'ADMINISTRATOR');
  upsertESA_USER_ROLE('ifactor', 'NOTIFI_WS');
  upsertESA_USER_ROLE('ifactor', 'AGGREGATOR');
  upsertESA_USER_ROLE('awesome', 'IFACTOR');
  upsertESA_USER_ROLE('awesome', 'USER');
  upsertESA_USER_ROLE('awesome', 'ADMINISTRATOR');
  upsertESA_USER_ROLE('awesome', 'NOTIFI_WS');
  upsertESA_USER_ROLE('awesome', 'AGGREGATOR');
  upsertESA_USER_ROLE('notifi', 'SSO_CLIENT');
  upsertESA_USER_ROLE('notifi-admin', 'SSO_CLIENT');
  upsertESA_USER_ROLE('sc-admin', 'SSO_CLIENT');
  upsertESA_USER_ROLE('bigoh_client', 'NOTIFI_WS');
  upsertESA_USER_ROLE('stitch_client', 'IFACTOR');
  upsertESA_USER_ROLE('stitch_client', 'NOTIFI_WS');
END;
/
