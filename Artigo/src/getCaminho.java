private List<Coordenadas> getCaminho() {
	List<Coordenadas> caminho = new ArrayList<>();
	int x = this.portalFim.x;
	int y = this.portalFim.y;
	while (x != this.portalInicio.x && y != this.portalInicio.y) {
		int auxX = x;
		int auxY = y;
		x = this.edgeTo[auxX][auxY].x;
		y = this.edgeTo[auxX][auxY].y;
		caminho.add(new Coordenadas(x, y));
	}
	return caminho;
}