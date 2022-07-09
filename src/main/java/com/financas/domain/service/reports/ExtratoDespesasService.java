package com.financas.domain.service.reports;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.financas.domain.exception.ReportException;
import com.financas.domain.model.Despesa;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ExtratoDespesasService {

	public byte[] emitirExtratoDespesas(Despesa despesa) {
		try {
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/extratos/extrato-despesa.jasper");
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var dataSource = new JRBeanCollectionDataSource(Arrays.asList(despesa));
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possível emitir o extrato de despesas", e);
		}
	}
	
}
