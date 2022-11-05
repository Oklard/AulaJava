select i.idVenda
     , i.valorVenda
     , i.dataVenda
     , idCliente
     , i.idCarro
  from VendaVeiculo
  where idCliente = idCarro.idCliente
  order by idVenda
