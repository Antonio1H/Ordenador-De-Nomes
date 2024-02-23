import java.util.Scanner;

public class NomesOrdenados {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nomes = new String[5];
        int nPreenchido = 0;
        String nome;
        int opcao;
        int flag = 0;
        

        do {
            System.out.println("Exemplo da tela principal do programa\n");
            System.out.println("Opções disponíveis:");
            System.out.println("1 - Cadastrar novo nome");
            System.out.println("2 - Exibir todos os nomes");
            System.out.println("3 - Ordenar em ordem crescente");
            System.out.println("4 - Ordenar em ordem decrescente");
            System.out.println("5 - Realizar busca");
            System.out.println("0 - Sair do programa");
            System.out.print("\nInforme a opção desejada: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome a ser cadastrado: ");
                    nome = sc.nextLine();
                    int insercao = insercao(nomes, nPreenchido, nome);

                    if (insercao == -1) {
                        System.out.println("O vetor está cheio. Não é possível adicionar mais nomes.");
                    } else if (insercao == -2) {
                        System.out.println("Nome repetido. Não pode ser adicionado novamente.");
                    } else {
                        System.out.println("Nome adicionado ao vetor na posição " + nPreenchido);
                        nPreenchido++;
                    }
                    flag = 1;
                    break;
                case 2:
                    exibirNomes(nomes, nPreenchido);
                    break;
                case 3:
                    insertionSort(nomes, nPreenchido);
                    System.out.println("Nomes ordenados em ordem crescente.");
                    flag = 2;
                    break;
                case 4:
                    selectionSort(nomes, nPreenchido);
                    System.out.println("Nomes ordenados em ordem decrescente.");
                    flag = 3;
                    break;
                case 5:
                    System.out.print("Digite o nome a ser buscado: ");
                    nome = sc.nextLine();

                    buscaNome(nomes, nPreenchido, nome, flag);
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println(); // Linha em branco para melhorar a visualização
        } while (opcao != 0);

        sc.close();
    }

    public static int insercao(String[] nomes, int nPreenchido, String nome) {
        int tamanho = nomes.length;

        if (nPreenchido == tamanho) {
            return -1; // Vetor cheio
        } else {
            for (int i = 0; i < nPreenchido; i++) {
                if (nomes[i].equals(nome)) {
                    return -2; // Nome repetido
                }
            }
        }

        nomes[nPreenchido] = nome;
        return 1; // Nome adicionado com sucesso
    }

    public static void exibirNomes(String[] nomes, int nPreenchido) {
        if (nPreenchido == 0) {
            System.out.println("Nenhum nome cadastrado.");
        } else {
            System.out.println("Lista de nomes:");
            for (int i = 0; i < nPreenchido; i++) {
                System.out.println(nomes[i]);
            }
        }
    }

    public static void insertionSort(String[] v, int n) {
        int fim = n - 1;

        for (int i = 1; i <= fim; i++) {
            String chave = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].compareTo(chave) > 0) {
                v[j + 1] = v[j];
                j--;
            }

            v[j + 1] = chave;
        }
    }

    public static void selectionSort(String[] v, int n) {
        int fim = n - 1;

        for (int i = 0; i < fim; i++) {
            int maior = i;

            for (int j = i + 1; j <= fim; j++) {
                if (v[j].compareTo(v[maior]) > 0) {
                    maior = j;
                }
            }

            String aux = v[i];
            v[i] = v[maior];
            v[maior] = aux;
        }
    }

    public static void buscaNome(String[] vetor, int quantidade, String nome, int flag) {
        if (flag == 1) {
            // Vetor não ordenado, chama busca sequencial simples
            int posicao = buscaSequencial(vetor, quantidade, nome);
            if (posicao != -1) {
                System.out.println("Nome encontrado na posição " + posicao);
            } else {
                System.out.println("Nome não encontrado");
            }
        } else if (flag == 2) {
            // Vetor ordenado em ordem crescente, chama busca binária
            int posicao = buscaBinaria(vetor, quantidade, nome);
            if (posicao != -1) {
                System.out.println("Nome encontrado na posição " + posicao);
            } else {
                System.out.println("Nome não encontrado");
            }
        } else if (flag == 3) {
            // Vetor ordenado em ordem decrescente, chama busca binária decrescente
            int posicao = buscaBinariaDecrescente(vetor, quantidade, nome);
            if (posicao != -1) {
                System.out.println("Nome encontrado na posição " + posicao);
            } else {
                System.out.println("Nome não encontrado");
            }
        } else {
            System.out.println("Flag inválido");
        }
    }

    public static int buscaSequencial(String[] vetor, int quantidade, String nome) {
        for (int i = 0; i < quantidade; i++) {
            if (vetor[i].equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscaBinaria(String[] vetor, int quantidade, String nome) {
        int inicio = 0;
        int fim = quantidade - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = vetor[meio].compareTo(nome);

            if (comparacao == 0) {
                return meio; // Nome encontrado
            } else if (comparacao < 0) {
                inicio = meio + 1; // O nome está à direita do meio
            } else {
                fim = meio - 1; // O nome está à esquerda do meio
            }
        }

        return -1; // Nome não encontrado
    }

    public static int buscaBinariaDecrescente(String[] vetor, int quantidade, String nome) {
        int inicio = 0;
        int fim = quantidade - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = vetor[meio].compareTo(nome);

            if (comparacao == 0) {
                return meio; // Nome encontrado
            } else if (comparacao > 0) {
                inicio = meio + 1; // O nome está à direita do meio
            } else {
                fim = meio - 1; // O nome está à esquerda do meio
            }
        }
        return -1; // Nome não encontrado
    }
}
