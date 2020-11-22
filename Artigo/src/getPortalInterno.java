private Coordenadas getPortalInterno(String portal) {
	int x = ( this.altura / 2 );
	int y = ( this.largura / 2 );
	int nrDist = 0;
	Coordenadas portalInterno = null;
	for (int i = x; i > 0; i--) {
		if ( !this.mat[i][y].isBlank() ) break;
		nrDist++;
	}
	int posX = ( x - nrDist );
	int posY = ( y - nrDist );
	int tam = posX + nrDist * 2;
	boolean achou = false;
	for (int i = posX; i < tam; i++) {
		if (i == posX || i == tam - 1) {
			for (int j = posY; j < tam; j++) {
				if (this.mat[i][j].equals(portal)) {
					portalInterno = new Coordenadas(i, j);
					achou = true;
					break;
				}
			}
		} else {
			if (this.mat[i][posX].equals(portal)) {
				portalInterno = new Coordenadas(i, posX);
				break;
			} else if (this.mat[i][tam - 1].equals(portal)) {
				portalInterno = new Coordenadas(i, tam - 1);
				break;
			}
			if (achou) break;
		}
	}
	return portalInterno;
}