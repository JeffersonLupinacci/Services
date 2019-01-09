package com.jeffersonlupinacci.app.schedulerService.serializablesDTO;

import java.io.Serializable;

/**
 * The Operation Summary
 *
 * @author jeffersonlupinacci
 */
public class OperationSummary implements Serializable {

  /**
   * The operation result
   */
  private boolean result;

  /**
   * @return the Result
   */
  public boolean isResult() {
    return result;
  }

  /**
   * @param result the Operation Result
   * @return this
   */
  public OperationSummary setResult(boolean result) {
    this.result = result;
    return this;
  }

  public OperationSummary() {
  }
}
