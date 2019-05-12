package br.com.bv.juliocesar.api.mappers;

import org.springframework.stereotype.Component;

import br.com.bv.juliocesar.api.entity.Mensagem;


@Component
public class MensagemMapper {
	
	public Mensagem loadMensagem() {
		
		Mensagem m = new Mensagem();
		m.setNumero_casas("8");
		m.setToken("473ea6e3edbecd2f4ba2b9ffdf1fe2522eeb920a");
		m.setCifrado("rclom i uiv jg pqa ycmabqwva zibpmz bpiv jg pqa ivaemza. dwtbiqzm");
		m.setDecifrado("");
		m.setResumo_criptografico("");
		
		return m;
	}

}
