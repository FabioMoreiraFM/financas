set foreign_key_checks = 0;

delete from agenda;
delete from despesa;
delete from grupo;
delete from grupo_permissao;
delete from oauth_client_details;
delete from parcela_despesa;
delete from permissao;
delete from receita;
delete from regras;
delete from terceiro;
delete from tipo_despesa;
delete from tipo_receita;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table agenda auto_increment = 1;
alter table despesa auto_increment = 1;
alter table parcela_despesa auto_increment = 1;
alter table receita auto_increment = 1;
alter table regras auto_increment = 1;
alter table terceiro auto_increment = 1;
alter table tipo_despesa auto_increment = 1;
alter table tipo_receita auto_increment = 1;
alter table usuario auto_increment = 1;
alter table usuario_grupo auto_increment = 1;

INSERT INTO regras (idRegras, qtdMaxEnvioAgendamento, fatorDespesaReceitaEnvioEmail, enviarEmailDespesasAtrasadas) VALUES(1, 3, 0.9, 1);
INSERT INTO terceiro (idTerceiro, descricao, documento, tipoTerceiro) VALUES(1, 'Terceiro PF', '52737850061', 'PF');
INSERT INTO terceiro (idTerceiro, descricao, documento, tipoTerceiro) VALUES(2, 'Terceiro PJ', '62282040000120', 'PJ');
INSERT INTO tipo_despesa (idTipoDespesa, descricao) VALUES(1, 'Boleto');
INSERT INTO tipo_receita (idTipoReceita, descricao) VALUES(1, 'Salário');
-- Senha 123
INSERT INTO usuario (idUsuario, nome, email, senha, cep, logradouro, bairro, cidade, uf, celular, dataCadastro) VALUES(1, 'Fabio Moreira', 'teste@teste.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', '88111600', 'Rua do teste', 'teste', 'teste', 'sc', '4899999', '2022-05-14 00:00:00.0');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, `scope`, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES('financas-web', NULL, '$2y$12$w3igMjsfS5XoAYuowoH3C.54vRFWlcXSHLjX7MwF990Kc2KKKh72e', 'READ,WRITE', 'password', NULL, NULL, 21600, 5184000, NULL, NULL);
INSERT INTO despesa (idDespesa, descricao, valorTotal, valorParcela, totalParcelas, credor, mesAnoInicioPgto, dtQuitacao, diaVencimentoParcela, idTipoDespesa, idUsuario) VALUES(1, 'Nova despesa', 500.00, 500.00, 1, 1, '2022-04-21', NULL, 31, 1, 1);
INSERT INTO hibernate_sequence (next_val) VALUES(1);
