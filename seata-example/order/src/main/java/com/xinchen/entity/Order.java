package com.xinchen.entity;

import java.math.BigDecimal;

/**
 */

public class Order {
  private Long id;

  private Long userId;

  private Long productId;

  private Integer count;

  private BigDecimal money;

  /**订单状态：0：创建中；1：已完结*/
  private Integer status;

  /**
   * Gets the value of id.
   *
   * @return the value of id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id id
   * @return this
   */
  public Order setId(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Gets the value of userId.
   *
   * @return the value of userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Sets the userId.
   *
   * @param userId userId
   * @return this
   */
  public Order setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Gets the value of productId.
   *
   * @return the value of productId
   */
  public Long getProductId() {
    return productId;
  }

  /**
   * Sets the productId.
   *
   * @param productId productId
   * @return this
   */
  public Order setProductId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Gets the value of count.
   *
   * @return the value of count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * Sets the count.
   *
   * @param count count
   * @return this
   */
  public Order setCount(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Gets the value of money.
   *
   * @return the value of money
   */
  public BigDecimal getMoney() {
    return money;
  }

  /**
   * Sets the money.
   *
   * @param money money
   * @return this
   */
  public Order setMoney(BigDecimal money) {
    this.money = money;
    return this;
  }

  /**
   * Gets the value of status.
   *
   * @return the value of status
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * Sets the status.
   *
   * @param status status
   * @return this
   */
  public Order setStatus(Integer status) {
    this.status = status;
    return this;
  }
}
