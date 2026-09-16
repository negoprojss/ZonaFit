package zona_fit.precentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class Zona_Fit_App {
    public static void main(String[] args) {
        ZonaFitApp();
    }

    private static void ZonaFitApp() {
        var salida = false;
        var clienteDAO = new ClienteDAO();
        Scanner consola = new Scanner(System.in);
        try {
            while (!salida){
                var opcion = motrarMenu(consola);
                salida = ejecutarOpciones(opcion,consola,clienteDAO);
            }
        }catch (Exception e){
            System.out.println("Error ZonaFit encontrado: "+e.getMessage());
        }

    }

    private static int motrarMenu(Scanner consola) {
        System.out.print("""
                Menu:
                1.Listar Cliente
                2.Buscar Cliente
                3.Agregar Cliente
                4.Modificar Cliente
                5.Eliminar Cliente
                6.Salir
                Elige una opcion:""");
        // Leemos y retornamos la opcion seleccionada
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, ClienteDAO clienteDAO) {
        var salir = false;
        switch (opcion){
            case 1 ->listaCliente(clienteDAO);
            case 2 ->buscarClientePorID(clienteDAO,consola);
            case 3 ->agregarCliente(clienteDAO,consola);
            case 4 ->modificarCliente(clienteDAO,consola);
            case 5 ->eliminarCliente(clienteDAO,consola);
            case 6 -> {
                System.out.println("**** Regresa Pronto! ****");
                salir= true;
            }
        }
        return salir;
    }

    private static void listaCliente(ClienteDAO clienteDAO) {
        ///  listar clientes
        System.out.println("++ lista de clientes ++");
        var clientes = clienteDAO.listaCliente();
        clientes.forEach(System.out::println);
    }

    private static void buscarClientePorID(ClienteDAO clienteDAO, Scanner consola) {
        System.out.println("Escrube el id del cliente a buscar: ");
        var idClinte = Integer.parseInt(consola.nextLine());
        var cliente1 = new Cliente(idClinte);
        System.out.println("antes de la busqueda: "+cliente1+ "\n" );
        var encontrado = clienteDAO.buscarClientePorID(cliente1);
        if (encontrado)
            System.out.println("\n Cliente encontrado: "+cliente1);
        else
            System.out.println("\n no se encntro nungun cliente con estos datos: "+cliente1.getId());
    }

    private static void agregarCliente(ClienteDAO clienteDAO,Scanner consola) {
        ///  Agregar Cliente
            System.out.println("escribe nombre: ");
            var nombreCliente = consola.nextLine();
            System.out.println("escribe los apellidos: ");
            var apellidoCliente = consola.nextLine();
            System.out.println("escribe la membresia: ");
            var membreciaCliente = Integer.parseInt(consola.nextLine());

            var nuevoClieten = new Cliente(nombreCliente,apellidoCliente,membreciaCliente);
            var agregado = clienteDAO.agregarCliente(nuevoClieten);
            if (agregado)
                System.out.println("se agrego: "+nuevoClieten);
            else
                System.out.println("nose puedo agregar: "+clienteDAO);
    }

    private static void modificarCliente(ClienteDAO clienteDAO, Scanner consola) {
        ///  Modificar cliente
        System.out.println("Que cliente quieres modificar (id)? ");
        var idCliente = Integer.parseInt(consola.nextLine());
        System.out.println("escribe nombre: ");
        var nombreCliente = consola.nextLine();
        System.out.println("escribe los apellidos: ");
        var apellidoCliente = consola.nextLine();
        System.out.println("escribe la membresia: ");
        var membreciaCliente = Integer.parseInt(consola.nextLine());

        var clienteModificado = new Cliente(idCliente,nombreCliente,apellidoCliente,membreciaCliente);
        var modificadoCliente = clienteDAO.modificarCliente(clienteModificado);
        if (modificadoCliente)
            System.out.println("se modifica correcto: "+clienteModificado);
        else
            System.out.println("no se modifico cliente"+clienteModificado);
    }

    private static void eliminarCliente(ClienteDAO clienteDAO, Scanner consola) {
        /// Eliminar cliene
        System.out.println("Que usuario quieres eliminar (id)?");
        var idCliente = Integer.parseInt(consola.nextLine());
        var elinarCliente = new Cliente(idCliente);
        var eliminadoCliente = clienteDAO.eliminarCliente(elinarCliente);
        if (eliminadoCliente)
            System.out.println("se eliminado correcto:" +clienteDAO );
        else
            System.out.println("no se eliminado cliente" +clienteDAO );
    }

}
