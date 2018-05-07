package com.joiest.jpf.facade;


import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.entity.BankInfo;

import java.util.List;

public interface BankServiceFacade {

    public Integer getBankCount();

    public List<BankInfo> getBank(long page, long rows);

    public JpfResponseDto addBank(String paybankname, String tpid, String bankcode);

    public BankInfo getBankInfo(String id);

    public JpfResponseDto editBank(String id, String paybankname, String tpid, String bankcode);

    public JpfResponseDto delBank(String id);

    /**
     * 获取所有银行信息
     */
    public List<BankInfo> getBankAll();

}
