package org.young.logging.logback;

import org.springframework.data.elasticsearch.annotations.Document;
import org.young.commons.protocols.ErrorLog;

@Document(indexName="error_log_index", type="errorlog")
public class ErrorLogExt extends ErrorLog {

	private static final long serialVersionUID = 1L;

}
