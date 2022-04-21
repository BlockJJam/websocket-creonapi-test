package com.test.socket.external.dashin.cputil;

import com4j.*;

/**
 * ICpCodeMgr �������̽�
 */
@IID("{6893A6D3-03FD-46EB-BB0E-28A22D3AEC9B}")
public interface ICpCodeMgr extends Com4jObject {
  // Methods:
  /**
   * <p>
   * method GetGroupCodeList
   * </p>
   * @param groupCode Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  @ReturnValue(type=NativeType.VARIANT)
  Object getGroupCodeList(
    int groupCode);


  /**
   * <p>
   * method CodeToName
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  String codeToName(
    String code);


  /**
   * <p>
   * method GetUsCodeList
   * </p>
   * @param type Optional parameter. Default value is 1
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.VARIANT)
  Object getUsCodeList(
    @Optional @DefaultValue("1") short type);


  /**
   * <p>
   * method GetUsCodeName
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  String getUsCodeName(
    String code);


  /**
   * <p>
   * method GetStockMarginRate
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  int getStockMarginRate(
    String code);


  /**
   * <p>
   * method GetStockMemeMin
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  int getStockMemeMin(
    String code);


  /**
   * <p>
   * method GetStockElwBasketCodeList
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockElwBasketCodeList(
    String bstrCode);


  /**
   * <p>
   * method GetStockElwBasketCompList
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockElwBasketCompList(
    String bstrCode);


  /**
   * <p>
   * method GetMarketStartTime
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(15)
  short getMarketStartTime();


  /**
   * <p>
   * method GetMarketEndTime
   * </p>
   * @return  Returns a value of type short
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  short getMarketEndTime();


  /**
   * <p>
   * method GetStockIndustryCode
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(17)
  String getStockIndustryCode(
    String bstrCode);


  /**
   * <p>
   * method GetStockMarketKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_MARKET_KIND
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(18)
  com.test.socket.external.dashin.cputil.CPE_MARKET_KIND getStockMarketKind(
    String bstrCode);


  /**
   * <p>
   * method GetStockControlKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_CONTROL_KIND
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(19)
  com.test.socket.external.dashin.cputil.CPE_CONTROL_KIND getStockControlKind(
    String bstrCode);


  /**
   * <p>
   * method GetStockSupervisionKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_SUPERVISION_KIND
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(20)
  com.test.socket.external.dashin.cputil.CPE_SUPERVISION_KIND getStockSupervisionKind(
    String bstrCode);


  /**
   * <p>
   * method GetStockCapital
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_CAPITAL_SIZE
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(21)
  com.test.socket.external.dashin.cputil.CPE_CAPITAL_SIZE getStockCapital(
    String bstrCode);


  /**
   * <p>
   * method GetStockFiscalMonth
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(22)
  int getStockFiscalMonth(
    String bstrCode);


  /**
   * <p>
   * method GetStockGroupCode
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(17) //= 0x11. The runtime will prefer the VTID if present
  @VTID(23)
  int getStockGroupCode(
    String bstrCode);


  /**
   * <p>
   * method GetStockKospi200Kind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_KOSPI200_KIND
   */

  @DISPID(18) //= 0x12. The runtime will prefer the VTID if present
  @VTID(24)
  com.test.socket.external.dashin.cputil.CPE_KOSPI200_KIND getStockKospi200Kind(
    String bstrCode);


  /**
   * <p>
   * method GetStockStatusKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_STOCK_STATUS_KIND
   */

  @DISPID(28) //= 0x1c. The runtime will prefer the VTID if present
  @VTID(25)
  com.test.socket.external.dashin.cputil.CPE_STOCK_STATUS_KIND getStockStatusKind(
    String bstrCode);


  /**
   * <p>
   * method GetGroupList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(19) //= 0x13. The runtime will prefer the VTID if present
  @VTID(26)
  @ReturnValue(type=NativeType.VARIANT)
  Object getGroupList();


  /**
   * <p>
   * method GetGroupName
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(20) //= 0x14. The runtime will prefer the VTID if present
  @VTID(27)
  String getGroupName(
    String bstrCode);


  /**
   * <p>
   * method GetIndustryList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(21) //= 0x15. The runtime will prefer the VTID if present
  @VTID(28)
  @ReturnValue(type=NativeType.VARIANT)
  Object getIndustryList();


  /**
   * <p>
   * method GetKrxIndustryList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(22) //= 0x16. The runtime will prefer the VTID if present
  @VTID(29)
  @ReturnValue(type=NativeType.VARIANT)
  Object getKrxIndustryList();


  /**
   * <p>
   * method GetIndustryName
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(23) //= 0x17. The runtime will prefer the VTID if present
  @VTID(30)
  String getIndustryName(
    String bstrCode);


  /**
   * <p>
   * method GetMemberList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(24) //= 0x18. The runtime will prefer the VTID if present
  @VTID(31)
  @ReturnValue(type=NativeType.VARIANT)
  Object getMemberList();


  /**
   * <p>
   * method GetMemberName
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(25) //= 0x19. The runtime will prefer the VTID if present
  @VTID(32)
  String getMemberName(
    String bstrCode);


  /**
   * <p>
   * method GetKosdaqIndustry1List
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(26) //= 0x1a. The runtime will prefer the VTID if present
  @VTID(33)
  @ReturnValue(type=NativeType.VARIANT)
  Object getKosdaqIndustry1List();


  /**
   * <p>
   * method GetKosdaqIndustry2List
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(27) //= 0x1b. The runtime will prefer the VTID if present
  @VTID(34)
  @ReturnValue(type=NativeType.VARIANT)
  Object getKosdaqIndustry2List();


  /**
   * <p>
   * method GetStockListByMarket
   * </p>
   * @param marketKind Mandatory dashin.cputil.CPE_MARKET_KIND parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(29) //= 0x1d. The runtime will prefer the VTID if present
  @VTID(35)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockListByMarket(
    com.test.socket.external.dashin.cputil.CPE_MARKET_KIND marketKind);


  /**
   * <p>
   * method GetStockSectionKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_KSE_SECTION_KIND
   */

  @DISPID(30) //= 0x1e. The runtime will prefer the VTID if present
  @VTID(36)
  com.test.socket.external.dashin.cputil.CPE_KSE_SECTION_KIND getStockSectionKind(
    String bstrCode);


  /**
   * <p>
   * method GetStockLacKind
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_LAC_KIND
   */

  @DISPID(31) //= 0x1f. The runtime will prefer the VTID if present
  @VTID(37)
  com.test.socket.external.dashin.cputil.CPE_LAC_KIND getStockLacKind(
    String bstrCode);


  /**
   * <p>
   * method GetStockCodeByName
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(32) //= 0x20. The runtime will prefer the VTID if present
  @VTID(38)
  String getStockCodeByName(
    String bstrName);


  /**
   * <p>
   * method GetStockListedDate
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(33) //= 0x21. The runtime will prefer the VTID if present
  @VTID(39)
  int getStockListedDate(
    String bstrName);


  /**
   * <p>
   * method GetStockMaxPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(34) //= 0x22. The runtime will prefer the VTID if present
  @VTID(40)
  int getStockMaxPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockMinPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(35) //= 0x23. The runtime will prefer the VTID if present
  @VTID(41)
  int getStockMinPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockParPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(36) //= 0x24. The runtime will prefer the VTID if present
  @VTID(42)
  int getStockParPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockStdPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(37) //= 0x25. The runtime will prefer the VTID if present
  @VTID(43)
  int getStockStdPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockYdOpenPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(38) //= 0x26. The runtime will prefer the VTID if present
  @VTID(44)
  int getStockYdOpenPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockYdHighPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(39) //= 0x27. The runtime will prefer the VTID if present
  @VTID(45)
  int getStockYdHighPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockYdLowPrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(40) //= 0x28. The runtime will prefer the VTID if present
  @VTID(46)
  int getStockYdLowPrice(
    String bstrName);


  /**
   * <p>
   * method GetStockYdClosePrice
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(41) //= 0x29. The runtime will prefer the VTID if present
  @VTID(47)
  int getStockYdClosePrice(
    String bstrName);


  /**
   * <p>
   * method GetStockParPriceChageType
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_ECT_PARPRICE_CHANGE
   */

  @DISPID(42) //= 0x2a. The runtime will prefer the VTID if present
  @VTID(48)
  com.test.socket.external.dashin.cputil.CPE_ECT_PARPRICE_CHANGE getStockParPriceChageType(
    String bstrName);


  /**
   * <p>
   * method IsStockCreditEnable
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(43) //= 0x2b. The runtime will prefer the VTID if present
  @VTID(49)
  int isStockCreditEnable(
    String bstrName);


  /**
   * <p>
   * method GetVentureKind
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_ECT_VENTURE_KIND
   */

  @DISPID(44) //= 0x2c. The runtime will prefer the VTID if present
  @VTID(50)
  com.test.socket.external.dashin.cputil.CPE_ECT_VENTURE_KIND getVentureKind(
    String bstrName);


  /**
   * <p>
   * method IsStockLoanEnable
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(45) //= 0x2d. The runtime will prefer the VTID if present
  @VTID(51)
  int isStockLoanEnable(
    String bstrName);


  /**
   * <p>
   * method GetStockEngName
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(46) //= 0x2e. The runtime will prefer the VTID if present
  @VTID(52)
  String getStockEngName(
    String bstrCode);


  /**
   * <p>
   * method GetWorkDate
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(47) //= 0x2f. The runtime will prefer the VTID if present
  @VTID(53)
  String getWorkDate();


  /**
   * <p>
   * method IsSPAC
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(54)
  int isSPAC(
    String bstrName);


  /**
   * <p>
   * method IsLendingStockEnable
   * </p>
   * @param bstrName Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(49) //= 0x31. The runtime will prefer the VTID if present
  @VTID(55)
  int isLendingStockEnable(
    String bstrName);


  /**
   * <p>
   * method GetMiniFutureList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(50) //= 0x32. The runtime will prefer the VTID if present
  @VTID(56)
  @ReturnValue(type=NativeType.VARIANT)
  Object getMiniFutureList();


  /**
   * <p>
   * method GetMiniOptionList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(51) //= 0x33. The runtime will prefer the VTID if present
  @VTID(57)
  @ReturnValue(type=NativeType.VARIANT)
  Object getMiniOptionList();


  /**
   * <p>
   * method ReLoadPortData
   * </p>
   */

  @DISPID(52) //= 0x34. The runtime will prefer the VTID if present
  @VTID(58)
  void reLoadPortData();


  /**
   * <p>
   * method GetTickUnit
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type double
   */

  @DISPID(53) //= 0x35. The runtime will prefer the VTID if present
  @VTID(59)
  double getTickUnit(
    String bstrCode);


  /**
   * <p>
   * method GetTickValue
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type double
   */

  @DISPID(54) //= 0x36. The runtime will prefer the VTID if present
  @VTID(60)
  double getTickValue(
    String bstrCode);


  /**
   * <p>
   * method OvFutCodeToName
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(55) //= 0x37. The runtime will prefer the VTID if present
  @VTID(61)
  String ovFutCodeToName(
    String code);


  /**
   * <p>
   * method OvFutGetAllCodeList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(56) //= 0x38. The runtime will prefer the VTID if present
  @VTID(62)
  @ReturnValue(type=NativeType.VARIANT)
  Object ovFutGetAllCodeList();


  /**
   * <p>
   * method OvFutGetExchList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(57) //= 0x39. The runtime will prefer the VTID if present
  @VTID(63)
  @ReturnValue(type=NativeType.VARIANT)
  Object ovFutGetExchList();


  /**
   * <p>
   * method OvFutGetLastTradeDate
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(58) //= 0x3a. The runtime will prefer the VTID if present
  @VTID(64)
  int ovFutGetLastTradeDate(
    String code);


  /**
   * <p>
   * method OvFutGetExchCode
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(59) //= 0x3b. The runtime will prefer the VTID if present
  @VTID(65)
  String ovFutGetExchCode(
    String code);


  /**
   * <p>
   * method OvFutGetProdCode
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(60) //= 0x3c. The runtime will prefer the VTID if present
  @VTID(66)
  String ovFutGetProdCode(
    String code);


  /**
   * <p>
   * method IsBigListingStock
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(61) //= 0x3d. The runtime will prefer the VTID if present
  @VTID(67)
  int isBigListingStock(
    String code);


  /**
   * <p>
   * method IsTradeCondition
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(62) //= 0x3e. The runtime will prefer the VTID if present
  @VTID(68)
  int isTradeCondition(
    String code);


  /**
   * <p>
   * method GetStartTime
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(63) //= 0x3f. The runtime will prefer the VTID if present
  @VTID(69)
  int getStartTime(
    String code);


  /**
   * <p>
   * method GetEndTime
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(64) //= 0x40. The runtime will prefer the VTID if present
  @VTID(70)
  int getEndTime(
    String code);


  /**
   * <p>
   * method IsFrnMember
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(65) //= 0x41. The runtime will prefer the VTID if present
  @VTID(71)
  int isFrnMember(
    String code);


  /**
   * <p>
   * method GetStockFutureList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(66) //= 0x42. The runtime will prefer the VTID if present
  @VTID(72)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockFutureList();


  /**
   * <p>
   * method GetStockFutureBaseList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(67) //= 0x43. The runtime will prefer the VTID if present
  @VTID(73)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockFutureBaseList();


  /**
   * <p>
   * method GetStockFutureListByBaseCode
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(68) //= 0x44. The runtime will prefer the VTID if present
  @VTID(74)
  @ReturnValue(type=NativeType.VARIANT)
  Object getStockFutureListByBaseCode(
    String bstrCode);


  /**
   * <p>
   * method GetStockFutureBaseCode
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(69) //= 0x45. The runtime will prefer the VTID if present
  @VTID(75)
  String getStockFutureBaseCode(
    String bstrCode);


  /**
   * <p>
   * method IsStockArrgSby
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(70) //= 0x46. The runtime will prefer the VTID if present
  @VTID(76)
  int isStockArrgSby(
    String code);


  /**
   * <p>
   * method IsStockIoi
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(71) //= 0x47. The runtime will prefer the VTID if present
  @VTID(77)
  int isStockIoi(
    String code);


  /**
   * <p>
   * method GetOverHeating
   * </p>
   * @param bstrCode Mandatory java.lang.String parameter.
   * @return  Returns a value of type dashin.cputil.CPE_ECT_OVERHEAT
   */

  @DISPID(72) //= 0x48. The runtime will prefer the VTID if present
  @VTID(78)
  com.test.socket.external.dashin.cputil.CPE_ECT_OVERHEAT getOverHeating(
    String bstrCode);


  /**
   * <p>
   * method IsStockLtgStkCnInsfItm
   * </p>
   * @param code Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(73) //= 0x49. The runtime will prefer the VTID if present
  @VTID(79)
  int isStockLtgStkCnInsfItm(
    String code);


  /**
   * <p>
   * method GetKostarOptionList
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(74) //= 0x4a. The runtime will prefer the VTID if present
  @VTID(80)
  @ReturnValue(type=NativeType.VARIANT)
  Object getKostarOptionList();


  // Properties:
}
