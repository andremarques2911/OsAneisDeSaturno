private Coordenadas getPortalExterno(String portal) {
	Coordenadas portalExterno = null;
	boolean achou = false;
	for (int i = 0; i < this.altura; i++) {
		if (i == 0 || i == this.altura - 1) {
			for (int j = 0; j < this.largura; j++) {
				if (this.mat[i][j].equals(portal)) {
					portalExterno = new Coordenadas(i, j);
					achou = true;
					break;
				}
			}
		} else {
			if (this.mat[i][0].equals(portal)) {
				portalExterno = new Coordenadas(i, 0);
				break;
			} else if (this.mat[i][this.largura - 1].equals(portal)) {
				portalExterno = new Coordenadas(i, this.largura - 1);
				break;
			}
		}
		if (achou) break;
	}
	return portalExterno;
}