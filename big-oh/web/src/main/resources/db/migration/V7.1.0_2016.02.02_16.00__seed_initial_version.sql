declare
  v_now BOH_OUTAGE.CREATED_AT%TYPE;
  v_outage_id BOH_OUTAGE.ID%TYPE;
  v_outage_detail_id BOH_OUTAGE_DETAIL.ID%TYPE;
  v_version_id BOH_VERSION.ID%TYPE;
  v_root_node_id BOH_VERSION_NODE.ID%TYPE;
  v_leaf_node_id BOH_VERSION_NODE.ID%TYPE;

BEGIN
  SELECT current_timestamp(3) INTO v_now FROM dual;

  SELECT BOH_OUTAGE_SEQ.nextval INTO v_outage_id FROM dual;
  INSERT INTO BOH_OUTAGE (ID, EXTERNAL_ID, RECEIVED_AT, CREATED_AT, UPDATED_AT)
    VALUES (v_outage_id, v_now, v_now, v_now, v_now);

  SELECT BOH_OUTAGE_DETAIL_SEQ.nextval INTO v_outage_detail_id FROM dual;
  INSERT INTO BOH_OUTAGE_DETAIL (ID, OUTAGE_ID, HASH, STATUS, CREATED_AT, UPDATED_AT, STARTED_AT, PROCESS_STATUS)
    VALUES (v_outage_detail_id, v_outage_id, '1', 'ACTIVE', v_now, v_now, v_now, 'EXTRACTED');

  SELECT BOH_VERSION_NODE_SEQ.nextval INTO v_root_node_id FROM dual;
  INSERT INTO BOH_VERSION_NODE (ID, UPDATED_AT, CREATED_AT)
    VALUES (v_root_node_id, v_now, v_now);

  SELECT BOH_VERSION_NODE_SEQ.nextval INTO v_leaf_node_id FROM dual;
  INSERT INTO BOH_VERSION_NODE (ID, UPDATED_AT, CREATED_AT)
    VALUES (v_leaf_node_id, v_now, v_now);

  SELECT BOH_VERSION_SEQ.nextval INTO v_version_id FROM dual;
  INSERT INTO BOH_VERSION (ID, VERSION_NODE_ID, UPDATED_AT, CREATED_AT, GENERATED_AT, PUBLISHED_AT)
    VALUES (v_version_id, v_root_node_id, v_now, v_now, v_now, v_now);

  INSERT INTO BOH_VERSION_NODE_REL (PARENT_NODE_ID, CHILD_NODE_ID)
    VALUES (v_root_node_id, v_leaf_node_id);

  INSERT INTO BOH_VERSION_OUTAGE_DETAIL (OUTAGE_DETAIL_ID, VERSION_NODE_ID)
    VALUES (v_outage_detail_id, v_leaf_node_id);
END;
