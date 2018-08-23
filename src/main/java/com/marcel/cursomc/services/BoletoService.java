package com.marcel.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.marcel.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preenchimentoPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoBoleto) {
		//caso fosse numa real geração de boleto, deveriamo pegar a data de vencimento do boleto
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoBoleto);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
