// ------------------------- DEFININDO CLASSES -------------------------
class Livro(val titulo: String, val autor: String, var disponivel: Boolean) {
    fun exibirDetalhes() {
        println("Título: $titulo, Autor: $autor, Disponível: ${if (disponivel) "Sim" else "Não"}")
    }
}

class Usuario(val nome: String) {
    val livrosEmprestados = mutableListOf<Livro>()

    fun emprestarLivro(livro: Livro) {
        if (livro.disponivel) {
            livrosEmprestados.add(livro)
            livro.disponivel = false
            println("$nome emprestou o livro: ${livro.titulo}")
        } else {
            println("O livro ${livro.titulo} não está disponível para empréstimo.")
        }
    }

    fun devolverLivro(livro: Livro) {
        if (livrosEmprestados.remove(livro)) {
            livro.disponivel = true
            println("$nome devolveu o livro: ${livro.titulo}")
        } else {
            println("$nome não possui o livro ${livro.titulo} para devolver.")
        }
    }
}

class Biblioteca(val nome: String) {
    val livros = mutableListOf<Livro>()

    fun adicionarLivro(livro: Livro) {
        livros.add(livro)
        println("O livro ${livro.titulo} foi adicionado à biblioteca.")
    }

    fun exibirLivrosDisponiveis() {
        println("Livros disponíveis na biblioteca $nome:")
        livros.filter { it.disponivel }.forEach { it.exibirDetalhes() }
    }
}

// ------------------------- FLUXO PRINCIPAL -------------------------
fun main() {
    // Criando classes
    val biblioteca = Biblioteca("Bibliotecaria")
    val usuario1 = Usuario("Fulano")
    val usuario2 = Usuario("Ciclano")

    // Adicionando  livros
    val livro1 = Livro("O Senhor dos Anéis", "J.R.R. TOLKIEN", true)
    val livro2 = Livro("Rangers - Ordem dos Arqueiros", "John Flanagan", true)
    val livro3 = Livro("As Crônicas de Nárnia", "C. S. Lewis", true)

    biblioteca.adicionarLivro(livro1)
    biblioteca.adicionarLivro(livro2)
    biblioteca.adicionarLivro(livro3)

    // Exibindo a lista de livros disponíveis antes de emprestar
    biblioteca.exibirLivrosDisponiveis()

    // Emprestando e devolvendo alguns livros
    usuario1.emprestarLivro(livro1)
    usuario2.emprestarLivro(livro2)

    // Exibindo a lista de livros disponíveis depois de emprestar
    biblioteca.exibirLivrosDisponiveis()

    // Devolver
    usuario1.devolverLivro(livro1)
    usuario2.devolverLivro(livro2)

    // Exibindo a lista de livros disponíveis depois de devolver
    biblioteca.exibirLivrosDisponiveis()
}
