public int encontraCaminho(String valorInicio, String valorFim) {
	Coordenadas coordenadas = this.getPortalExterno(valorInicio);
	this.portalInicio = coordenadas;
	this.portalFim = this.getPortalInterno(valorFim);
	this.bfs(coordenadas.x, coordenadas.y);
	List<Coordenadas> caminho = this.getCaminho();
	return caminho.size();
}