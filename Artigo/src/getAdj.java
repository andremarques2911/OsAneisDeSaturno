private List<Coordenadas> getAdj(int x, int y) {
	List<Coordenadas> adj = new ArrayList<>();
	if ( !(x - 1 < 0) ) {
		Coordenadas top = this.getCoordenadas(x - 1, y);
		if (top != null) adj.add(top);
	}
	if ( !(x + 1 >= this.altura) ) {
		Coordenadas bottom = this.getCoordenadas(x + 1, y);
		if (bottom != null) adj.add(bottom);
	}
	if ( !(y - 1 < 0) ) {
		Coordenadas left = this.getCoordenadas(x, y - 1);
		if (left != null) adj.add(left);
	}
	if ( !(y + 1 >= this.largura) ) {
		Coordenadas right = this.getCoordenadas(x, y + 1);
		if ( right != null ) adj.add(right);
	}
	if ( !this.mat[x][y].equals(".")
			&& !this.mat[x][y].equals("#")
			&& !this.mat[x][y].isBlank() ) {
		Coordenadas portal = this.getPortal(x, y);
		if (portal != null) adj.add(portal);
	}
	return adj;
}