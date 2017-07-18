CREATE OR REPLACE PROCEDURE upsertESA_ROLE(p_code        IN ESA_ROLE.CODE%TYPE,
                                           p_description IN ESA_ROLE.DESCRIPTION%TYPE)
IS
  BEGIN
    MERGE INTO ESA_ROLE
    USING DUAL
    ON (CODE = p_code)
    WHEN MATCHED THEN
    UPDATE SET
      DESCRIPTION = p_description,
      UPDATED_AT = CURRENT_TIMESTAMP
    WHEN NOT MATCHED THEN
    INSERT (
      ID,
      CODE,
      DESCRIPTION,
      CREATED_AT
    ) VALUES (
      ESA_ROLE_SEQ.NEXTVAL,
      p_code,
      p_description,
      CURRENT_TIMESTAMP
    );

    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE upsertESA_AUTHORITY(p_code        IN ESA_ROLE.CODE%TYPE,
                                                p_description IN ESA_ROLE.DESCRIPTION%TYPE)
IS
  BEGIN
    MERGE INTO ESA_AUTHORITY
    USING DUAL
    ON (CODE = p_code)
    WHEN MATCHED THEN
    UPDATE SET
      DESCRIPTION = p_description,
      UPDATED_AT = CURRENT_TIMESTAMP
    WHEN NOT MATCHED THEN
    INSERT (
      ID,
      CODE,
      DESCRIPTION,
      CREATED_AT
    ) VALUES (
      ESA_AUTH_SEQ.NEXTVAL,
      p_code,
      p_description,
      CURRENT_TIMESTAMP
    );

    COMMIT;
  END;
/

CREATE OR REPLACE PROCEDURE upsertESA_ROLE_AUTHORITY(p_role_code      IN ESA_ROLE.CODE%TYPE,
                                                     p_authority_code IN ESA_AUTHORITY.CODE%TYPE)
IS
  BEGIN
    DECLARE
      v_role_id      ESA_ROLE.ID%TYPE;
      v_authority_id ESA_AUTHORITY.ID%TYPE;
    BEGIN

      SELECT id
      INTO v_role_id
      FROM ESA_ROLE
      WHERE code = p_role_code;

      SELECT id
      INTO v_authority_id
      FROM ESA_AUTHORITY
      WHERE code = p_authority_code;

      INSERT INTO ESA_ROLE_AUTHORITY (
        ROLE_ID,
        AUTHORITY_ID
      )
        SELECT
          v_role_id,
          v_authority_id
        FROM
          DUAL
        WHERE
          NOT EXISTS(
              SELECT NULL
              FROM
                ESA_ROLE_AUTHORITY
              WHERE
                ROLE_ID = v_role_id AND
                AUTHORITY_ID = v_authority_id
          );
      COMMIT;
    END;
  END;
/

CREATE OR REPLACE PROCEDURE upsertESA_NAV_APPLICATION(p_code IN ESA_NAV_APPLICATION.CODE%TYPE,
                                                      p_name IN ESA_NAV_APPLICATION.NAME%TYPE)
IS
  BEGIN
    MERGE INTO ESA_NAV_APPLICATION
    USING DUAL
    ON (CODE = p_code)
    WHEN MATCHED THEN
    UPDATE SET
      NAME = p_name,
      UPDATED_AT = CURRENT_TIMESTAMP
    WHEN NOT MATCHED THEN
    INSERT (
      ID,
      CODE,
      NAME,
      CREATED_AT
    ) VALUES (
      ESA_NAV_APPLICATION_SEQ.NEXTVAL,
      p_code,
      p_name,
      CURRENT_TIMESTAMP
    );
  END;
/

CREATE OR REPLACE PROCEDURE upsertESA_NAV_SERVER(p_description  IN ESA_NAV_SERVER.DESCRIPTION%TYPE,
                                                 p_protocol     IN ESA_NAV_SERVER.PROTOCOL%TYPE,
                                                 p_host         IN ESA_NAV_SERVER.HOST%TYPE,
                                                 p_port         IN ESA_NAV_SERVER.PORT%TYPE,
                                                 p_context_path IN ESA_NAV_SERVER.CONTEXT_PATH%TYPE,
                                                 p_logout_path  IN ESA_NAV_SERVER.LOGOUT_PATH%TYPE)
IS
  BEGIN
    MERGE INTO ESA_NAV_SERVER
    USING DUAL
    ON (DESCRIPTION = p_description)
    WHEN MATCHED THEN
    UPDATE SET
      PROTOCOL = p_protocol,
      HOST = p_host,
      PORT = p_port,
      CONTEXT_PATH = p_context_path,
      LOGOUT_PATH = p_logout_path,
      UPDATED_AT = CURRENT_TIMESTAMP
    WHEN NOT MATCHED THEN
    INSERT (
      ID,
      PROTOCOL,
      HOST,
      PORT,
      CONTEXT_PATH,
      DESCRIPTION,
      LOGOUT_PATH,
      CREATED_AT
    ) VALUES (
      ESA_NAV_SERVER_SEQ.NEXTVAL,
      p_protocol,
      p_host,
      p_port,
      p_context_path,
      p_description,
      p_logout_path,
      CURRENT_TIMESTAMP
    );
  END;
/
CREATE OR REPLACE PROCEDURE upsertESA_NAV_VIEW(p_code               IN ESA_NAV_VIEW.CODE%TYPE,
                                               p_name               IN ESA_NAV_VIEW.NAME%TYPE,
                                               p_path               IN ESA_NAV_VIEW.PATH%TYPE,
                                               p_icon               IN ESA_NAV_VIEW.ICON%TYPE,
                                               p_server_description IN ESA_NAV_SERVER.DESCRIPTION%TYPE,
                                               p_application_code   IN ESA_NAV_APPLICATION.CODE%TYPE,
                                               p_sort               IN ESA_NAV_VIEW.SORT%TYPE,
                                               p_new_window         IN ESA_NAV_VIEW.NEW_WINDOW%TYPE)
IS
  BEGIN
    DECLARE
      v_server_id      ESA_NAV_SERVER.ID%TYPE;
      v_application_id ESA_NAV_APPLICATION.ID%TYPE;
    BEGIN

      SELECT id
      INTO v_server_id
      FROM ESA_NAV_SERVER
      WHERE DESCRIPTION = p_server_description;

      SELECT id
      INTO v_application_id
      FROM ESA_NAV_APPLICATION
      WHERE CODE = p_application_code;

      MERGE INTO ESA_NAV_VIEW
      USING DUAL
      ON (CODE = p_code)
      WHEN MATCHED THEN
      UPDATE SET
        NAME = p_name,
        PATH = p_path,
        ICON = p_icon,
        SORT = p_sort,
        UPDATED_AT = CURRENT_TIMESTAMP,
        NEW_WINDOW = p_new_window
      WHEN NOT MATCHED THEN
      INSERT (
        ID,
        CODE,
        NAME,
        PATH,
        ICON,
        SERVER_ID,
        APPLICATION_ID,
        SORT,
        CREATED_AT,
        NEW_WINDOW
      ) VALUES (
        ESA_NAV_VIEW_SEQ.NEXTVAL,
        p_code,
        p_name,
        p_path,
        p_icon,
        v_server_id,
        v_application_id,
        p_sort,
        CURRENT_TIMESTAMP,
        p_new_window
      );
    END;
  END;
/
CREATE OR REPLACE PROCEDURE upsertESA_AUTHORITY_NAV_VIEW(p_authority_code IN ESA_AUTHORITY.CODE%TYPE,
                                                         p_view_code      IN ESA_NAV_VIEW.CODE%TYPE)
IS
  BEGIN
    DECLARE
      v_authority_id ESA_AUTHORITY.ID%TYPE;
      v_view_id      ESA_NAV_VIEW.ID%TYPE;
    BEGIN

      SELECT id
      INTO v_authority_id
      FROM ESA_AUTHORITY
      WHERE code = p_authority_code;

      SELECT id
      INTO v_view_id
      FROM ESA_NAV_VIEW
      WHERE code = p_view_code;

      INSERT INTO ESA_AUTHORITY_NAV_VIEW (
        AUTHORITY_ID,
        VIEW_ID
      )
        SELECT
          v_authority_id,
          v_view_id
        FROM
          DUAL
        WHERE
          NOT EXISTS(
              SELECT NULL
              FROM
                ESA_AUTHORITY_NAV_VIEW
              WHERE
                AUTHORITY_ID = v_authority_id AND
                VIEW_ID = v_view_id
          );
      COMMIT;
    END;
  END;
/

CREATE OR REPLACE PROCEDURE upsertESA_USER(p_username             IN ESA_USER.USERNAME%TYPE,
                                           p_password             IN ESA_USER.PASSWORD%TYPE,
                                           p_salt                 IN ESA_USER.SALT%TYPE,
                                           p_enabled              IN ESA_USER.ENABLED%TYPE,
                                           p_expires_on           IN ESA_USER.EXPIRES_ON%TYPE,
                                           p_locked_until         IN ESA_USER.LOCKED_UNTIL%TYPE,
                                           p_description          IN ESA_USER.DESCRIPTION%TYPE,
                                           p_full_name            IN ESA_USER.FULL_NAME%TYPE,
                                           p_email_address        IN ESA_USER.EMAIL_ADDRESS%TYPE,
                                           p_password_expires_on  IN ESA_USER.PASSWORD_EXPIRES_ON%TYPE,
                                           p_email_verified       IN ESA_USER.EMAIL_VERIFIED%TYPE,
                                           p_verification_sent_on IN ESA_USER.VERIFICATION_SENT_ON%TYPE,
                                           p_last_logged_in       IN ESA_USER.LAST_LOGGED_IN%TYPE)
IS
  BEGIN

    MERGE INTO ESA_USER
    USING DUAL
    ON (USERNAME = p_username)
    WHEN MATCHED THEN
    UPDATE SET
      PASSWORD        = p_password,
      SALT          = p_salt,
      ENABLED        = p_enabled,
      EXPIRES_ON      = p_expires_on,
      LOCKED_UNTIL      = p_locked_until,
      DESCRIPTION      = p_description,
      FULL_NAME        = p_full_name,
      EMAIL_ADDRESS      = p_email_address,
      PASSWORD_EXPIRES_ON  = p_password_expires_on,
      EMAIL_VERIFIED    = p_email_verified,
      VERIFICATION_SENT_ON  = p_verification_sent_on,
      LAST_LOGGED_IN    = p_last_logged_in,
      UPDATED_AT      = CURRENT_TIMESTAMP
    WHEN NOT MATCHED THEN
    INSERT (
      ID,
      USERNAME,
      PASSWORD,
      SALT,
      ENABLED,
      EXPIRES_ON,
      LOCKED_UNTIL,
      DESCRIPTION,
      FULL_NAME,
      EMAIL_ADDRESS,
      PASSWORD_EXPIRES_ON,
      EMAIL_VERIFIED,
      VERIFICATION_SENT_ON,
      LAST_LOGGED_IN,
      CREATED_AT
    ) VALUES (
      ESA_USER_SEQ.NEXTVAL,
      p_username,
      p_password,
      p_salt,
      p_enabled,
      p_expires_on,
      p_locked_until,
      p_description,
      p_full_name,
      p_email_address,
      p_password_expires_on,
      p_email_verified,
      p_verification_sent_on,
      p_last_logged_in,
      CURRENT_TIMESTAMP
    );
  END;
/


CREATE OR REPLACE PROCEDURE upsertESA_USER_ROLE(p_username  IN ESA_USER.USERNAME%TYPE,
                                                p_role_code IN ESA_ROLE.CODE%TYPE)
IS
  BEGIN
    DECLARE
      v_user_id ESA_USER.ID%TYPE;
      v_role_id ESA_ROLE.ID%TYPE;
    BEGIN

      SELECT ID
      INTO v_user_id
      FROM ESA_USER
      WHERE USERNAME = p_username;

      SELECT ID
      INTO v_role_id
      FROM ESA_ROLE
      WHERE CODE = p_role_code;

      INSERT INTO ESA_USER_ROLE (
        USER_ID,
        ROLE_ID
      )
        SELECT
          v_user_id,
          v_role_id
        FROM
          DUAL
        WHERE
          NOT EXISTS(
              SELECT NULL
              FROM
                ESA_USER_ROLE
              WHERE
                USER_ID = v_user_id AND
                ROLE_ID = v_role_id
          );
      COMMIT;
    END;
  END;
/
