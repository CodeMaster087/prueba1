package com.proyecto.prueba1.model;

/**
 * Enumeración que define los posibles estados de una carga
 * durante su ciclo de vida en el sistema de transporte.
 */
public enum EstadoCarga {
    DISPONIBLE,      // La carga ha sido publicada y está lista para ser seleccionada.
    EN_PROCESO,      // La carga ha sido seleccionada por un conductor y está en tránsito.
    ENTREGADA,       // La carga ha llegado a su destino y la entrega ha sido confirmada.
    CANCELADA,       // La carga ha sido cancelada por el usuario publicador.
    FINALIZADA       // La carga ha sido entregada y se ha completado el ciclo (ej. pago).
}