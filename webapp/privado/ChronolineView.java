package privado;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author scalvd01
 */
@Named
@RequestScoped
public class ChronolineView {

    private List<Event> events;

    @PostConstruct
    public void init() {
        events = new ArrayList<>();
        events.add(new Event("Ordered", "15/10/2020 10:30", "pi pi-shopping-cart", "#9C27B0", "game-controller.jpg"));
        events.add(new Event("Processing", "15/10/2020 14:00", "pi pi-cog", "#673AB7"));
        events.add(new Event("Shipped", "15/10/2020 16:15", "pi pi-shopping-cart", "#FF9800"));
        events.add(new Event("Delivered", "16/10/2020 10:00", "pi pi-check", "#607D8B"));

       
    }

    public List<Event> getEvents() {
        return events;
    }

    public static class Event {
        private String status;
        private String date;
        private String icon;
        private String color;
        private String image;

        public Event() {
        }

        public Event(String status, String date, String icon, String color) {
            this.status = status;
            this.date = date;
            this.icon = icon;
            this.color = color;
        }

        public Event(String status, String date, String icon, String color, String image) {
            this.status = status;
            this.date = date;
            this.icon = icon;
            this.color = color;
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
