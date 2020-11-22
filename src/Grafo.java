import java.util.*;

class Grafo {
    private String[][] mat;
    private boolean[][] marcados;
    private Coordenadas[][] edgeTo;
    private Integer[][] distTo;
    private Coordenadas portalInicio;
    private Coordenadas portalFim;
    private int altura;
    private int largura;
    private int countLinha;

    public Grafo(int x, int y) {
        this.mat = new String[x][y];
        this.marcados = new boolean[x][y];
        this.edgeTo = new Coordenadas[x][y];
        this.distTo = new Integer[x][y];
        this.altura = x;
        this.largura = y;
        this.countLinha = 0;
    }

    public void inicializaMarcados() {
        for (int i = 0; i < this.altura; i++) {
            for (int j = 0; j < this.largura; j++) {
                this.marcados[i][j] = false;
            }
        }
    }

    public void insereLinha(String linha) {
        String[] elementos = linha.split("");
        for (int i=0; i < elementos.length; i++) {
            this.mat[this.countLinha][i] = elementos[i];
        }
        this.countLinha++;
    }

    private void bfs(int x, int y) {
        List<Coordenadas> q = new LinkedList<>();
        Coordenadas cood = new Coordenadas(x, y);
        q.add(cood);
        marcados[cood.x][cood.y] = true;
        edgeTo[x][y] = new Coordenadas(-1, -1);
        distTo[x][y] = 0;
        boolean acabou = false;
        while (!q.isEmpty()) {
            Coordenadas v = q.remove(0);
            for (Coordenadas w : this.getAdj(v.x, v.y)) {
                if ( !marcados[w.x][w.y] && !mat[w.x][w.y].equals("#") && !mat[w.x][w.y].equals(" ") ) {
                    Coordenadas to = new Coordenadas(w.x, w.y);
                    marcados[to.x][to.y] = true;
                    edgeTo[to.x][to.y] = new Coordenadas(v.x, v.y);
                    distTo[to.x][to.y] = distTo[v.x][v.y] + 1;
                    q.add(to);
                    if (w.x == this.portalFim.x && w.x == this.portalFim.y) acabou = true;
                }
            }
            if (acabou) break;
        }
    }

    private Coordenadas getPortalInterno(String portal) {
        int x = ( this.altura / 2 );
        int y = ( this.largura / 2 );
        int nrDist = 0;
        Coordenadas portalInterno = null;

        for (int i = x; i > 0; i--) {
            if ( !this.mat[i][y].equals(" ") ) break;
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
        if ( !this.mat[x][y].equals(".") && !this.mat[x][y].equals("#") && !this.mat[x][y].equals(" ") ) {
            Coordenadas portal = this.getPortal(x, y);
            if (portal != null) adj.add(portal);
        }
        return adj;
    }

    private Coordenadas getCoordenadas(int x, int y) {
        return new Coordenadas(x, y);
    }

    public int encontraCaminho(String valorInicio, String valorFim) {
        Coordenadas coordenadas = this.getPortalExterno(valorInicio);
        this.portalInicio = coordenadas;
        this.portalFim = this.getPortalInterno(valorFim);
        this.bfs(coordenadas.x, coordenadas.y);
        return this.distTo[this.portalFim.x][this.portalFim.y];
    }

    private Coordenadas getPortal(int x, int y) {
        if (x == 0 || x == this.altura - 1 || y == 0 || y == this.largura - 1) {
            return this.getPortalInterno(this.mat[x][y]);
        } else {
            return this.getPortalExterno(this.mat[x][y]);
        }
    }

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

    public void imprime() {
        for (int i = 0; i < this.altura; i++) {
            for (int j = 0; j < this.largura; j++) {
                System.out.print(this.mat[i][j]);
            }
            System.out.println();
        }
    }
}
