package app;

import java.util.Scanner;
import domain.Ahorro;
import domain.Corriente;
import service.IServiceCuenta;
import service.ServiceCuenta;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IServiceCuenta service = new ServiceCuenta();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENU CUENTAS =====");
            System.out.println("a. Listar todas las cuentas Ahorro");
            System.out.println("b. Listar todas las cuentas Corriente");
            System.out.println("c. Crear cuenta de Ahorro");
            System.out.println("d. Crear cuenta Corriente");
            System.out.println("e. Obtener la información de la cuenta por numero");
            System.out.println("f. Retirar Dinero");
            System.out.println("g. Depositar Dinero");
            System.out.println("h. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine().trim().toLowerCase();

            switch (opcion) {
                case "a":
                    listarAhorros(service);
                    break;
                case "b":
                    listarCorrientes(service);
                    break;
                case "c":
                    crearAhorro(sc, service);
                    break;
                case "d":
                    crearCorriente(sc, service);
                    break;
                case "e":
                    obtenerCuenta(sc, service);
                    break;
                case "f":
                    retirar(sc, service);
                    break;
                case "g":
                    depositar(sc, service);
                    break;
                case "h":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        sc.close();
    }

    private static void listarAhorros(IServiceCuenta service) {
        System.out.println("--- Cuentas de Ahorro ---");
        if (service.listarAhorros().isEmpty()) {
            System.out.println("No hay cuentas de ahorro registradas.");
        }
        for (Ahorro a : service.listarAhorros()) {
            System.out.println(a);
        }
    }

    private static void listarCorrientes(IServiceCuenta service) {
        System.out.println("--- Cuentas Corriente ---");
        if (service.listarCorrientes().isEmpty()) {
            System.out.println("No hay cuentas corriente registradas.");
        }
        for (Corriente c : service.listarCorrientes()) {
            System.out.println(c);
        }
    }

    private static void crearAhorro(Scanner sc, IServiceCuenta service) {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("DNI del cliente: ");
        long dni = Long.parseLong(sc.nextLine());
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha de creación: ");
        String fecha = sc.nextLine();

        Ahorro ahorro = new Ahorro(numeroCuenta, dni, saldo, fecha);
        service.crearAhorro(ahorro);
        System.out.println("Cuenta de ahorro creada con éxito.");
    }

    private static void crearCorriente(Scanner sc, IServiceCuenta service) {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("DNI del cliente: ");
        long dni = Long.parseLong(sc.nextLine());
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine());
        System.out.print("Impuesto: ");
        double impuesto = Double.parseDouble(sc.nextLine());

        Corriente corriente = new Corriente(numeroCuenta, dni, saldo, impuesto);
        service.crearCorriente(corriente);
        System.out.println("Cuenta corriente creada con éxito.");
    }

    private static void obtenerCuenta(Scanner sc, IServiceCuenta service) {
        System.out.print("Número de cuenta a buscar: ");
        String numeroCuenta = sc.nextLine();
        var cuenta = service.obtenerCuentaPorNumero(numeroCuenta);
        if (cuenta == null) {
            System.out.println("No se encontró la cuenta.");
        } else {
            System.out.println(cuenta);
        }
    }

    private static void retirar(Scanner sc, IServiceCuenta service) {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("Monto a retirar: ");
        double monto = Double.parseDouble(sc.nextLine());
        boolean exito = service.retirarDinero(numeroCuenta, monto);
        if (exito) {
            System.out.println("Retiro realizado con éxito.");
        }
    }

    private static void depositar(Scanner sc, IServiceCuenta service) {
        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("Monto a depositar: ");
        double monto = Double.parseDouble(sc.nextLine());
        boolean exito = service.depositarDinero(numeroCuenta, monto);
        if (exito) {
            System.out.println("Depósito realizado con éxito.");
        }
    }
}
