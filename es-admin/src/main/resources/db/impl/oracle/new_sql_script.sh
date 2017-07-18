#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set( $symbol_bracket_open = '{' )
#set( $symbol_bracket_closed = '}' )
#!/bin/bash
read -p "Describe script contents: " DESC
DESC=${symbol_dollar}${symbol_bracket_open}DESC//[ ]/_${symbol_bracket_closed}
NOW=$(date +"%Y.%m.%d_%H.%M")
FILE_NAME="V${es-admin-version}_${symbol_dollar}${symbol_bracket_open}NOW${symbol_bracket_closed}__${symbol_dollar}${symbol_bracket_open}DESC${symbol_bracket_closed}.sql"
touch ${symbol_dollar}${symbol_bracket_open}FILE_NAME${symbol_bracket_closed}
echo "Created an empty script: ${symbol_dollar}FILE_NAME"
