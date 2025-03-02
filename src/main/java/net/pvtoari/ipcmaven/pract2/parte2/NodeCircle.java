package net.pvtoari.ipcmaven.pract2.parte2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class NodeCircle {
    private final Circle value;
    private final DoubleProperty radius;
    private final ObjectProperty<Paint> fill, stroke;
    private final ChangeListener<Number> changeListenerRadius;
    private final ChangeListener<Paint> changeListenerStroke, changeListenerFill;

    public NodeCircle(Circle circle) {
        this.value = circle;
        this.radius = new SimpleDoubleProperty(circle.getRadius());
        this.stroke = new SimpleObjectProperty<>(circle.getStroke());
        this.fill = new SimpleObjectProperty<>(circle.getFill());
        this.changeListenerRadius = this::changeListenerRadiusMethod;
        this.changeListenerStroke = this::changeListenerStrokeMethod;
        this.changeListenerFill = this::changeListenerFillMethod;

        this.radius.addListener(changeListenerRadius);
        this.stroke.addListener(changeListenerStroke);
        this.fill.addListener(changeListenerFill);

        this.radius.bindBidirectional(circle.radiusProperty());
        this.stroke.bindBidirectional(circle.strokeProperty());
        this.fill.bindBidirectional(circle.fillProperty());
    }

    public double getRadius() {
        return this.radius.get();
    }

    public void setRadius(double radius) {
        this.radius.set(radius);
    }

    public Paint getStroke() {
        return this.stroke.get();
    }

    public void setStroke(Paint stroke) {
        this.stroke.set(stroke);
    }

    public Paint getFill() {
        return this.fill.get();
    }

    public void setFill(Paint fill) {
        this.fill.set(fill);
    }

    private void changeListenerRadiusMethod(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.println("Radius changed from " + oldValue + " to " + newValue);
    }

    private void changeListenerStrokeMethod(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
        System.out.println("Stroke changed from " + oldValue + " to " + newValue);
    }

    private void changeListenerFillMethod(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
        System.out.println("Fill changed from " + oldValue + " to " + newValue);
    }
}
