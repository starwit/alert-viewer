import {DeckGL} from "@deck.gl/react";
import {MapView} from '@deck.gl/core';
import {TileLayer} from "@deck.gl/geo-layers";
import {BitmapLayer} from "@deck.gl/layers";

function AlertMap() {
    // Set initial map position and zoom level
    const INITIAL_VIEW_STATE = {
        longitude: -86.13470,     // Initial longitude (X coordinate)
        latitude: 39.91,      // Initial latitude (Y coordinate)
        zoom: 10,            // Initial zoom level
        pitch: 0,           // No tilt
        bearing: 0          // No rotation
    };


    const layers = [
        createBaseMapLayer(),
    ];

    // Create map view settings - enable map repetition when scrolling horizontally
    const MAP_VIEW = new MapView({repeat: true});

    // Define map layers
    function createBaseMapLayer() {
        // Creating base map layer using CartoDB light theme
        return new TileLayer({
            // URL for map tiles
            data: "https://c.tile.openstreetmap.org/{z}/{x}/{y}.png",
            minZoom: 0,     // Minimum zoom level
            maxZoom: 19,    // Maximum zoom level
            tileSize: 256,  // Size of each map tile

            // Function to render each map tile
            renderSubLayers: props => {
                // Get geographical boundaries of the current tile
                const {
                    bbox: {west, south, east, north}
                } = props.tile;

                // Create image layer for the tile
                return new BitmapLayer(props, {
                    data: null,
                    image: props.data,
                    bounds: [west, south, east, north]
                });
            }
        })
    }

    return (
        <DeckGL
            layers={layers}               // Add map layers
            views={MAP_VIEW}              // Add map view settings
            initialViewState={INITIAL_VIEW_STATE}  // Set initial position
            controller={{dragRotate: false}}       // Disable rotation
        />
    );
}

export default AlertMap;