private Coordenadas getPortal(int x, int y) {
	if (x == 0 || x == this.altura - 1 || y == 0 || y == this.largura - 1) {
		return this.getPortalInterno(this.mat[x][y]);
	} else {
		return this.getPortalExterno(this.mat[x][y]);
	}
}