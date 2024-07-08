package com.poly.sms.controller.app;

import com.poly.sms.entity.Shipment;
import com.poly.sms.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Integer id) {
        Optional<Shipment> shipment = shipmentService.findById(id);
        if (shipment.isPresent()) {
            return ResponseEntity.ok(shipment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Shipment createShipment(@RequestBody Shipment shipment) {
        return shipmentService.save(shipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Integer id, @RequestBody Shipment shipmentDetails) {
        Optional<Shipment> shipment = shipmentService.findById(id);
        if (shipment.isPresent()) {
            Shipment updatedShipment = shipment.get();
            updatedShipment.setOrder(shipmentDetails.getOrder());
            updatedShipment.setShipmentDate(shipmentDetails.getShipmentDate());
            updatedShipment.setExpectedDeliveryDate(shipmentDetails.getExpectedDeliveryDate());
            updatedShipment.setDeliveryStatus(shipmentDetails.getDeliveryStatus());
            updatedShipment.setShippingAddress(shipmentDetails.getShippingAddress());
            updatedShipment.setShippingName(shipmentDetails.getShippingName());
            updatedShipment.setShippingPhone(shipmentDetails.getShippingPhone());
            updatedShipment.setBillingAddress(shipmentDetails.getBillingAddress());
            return ResponseEntity.ok(shipmentService.save(updatedShipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Integer id) {
        Optional<Shipment> shipment = shipmentService.findById(id);
        if (shipment.isPresent()) {
            shipmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
